/* ============================================================
   Bin to Bite – App Logic
   Features:
   - TensorFlow.js COCO-SSD live camera detection
   - Image upload + model scan
   - Manual ingredient entry
   - Spoonacular API recipe search (with caching)
   - Toast notifications
   ============================================================ */

// ===== CONFIG =====
const SPOONACULAR_API_KEY = '6f1c306270dd4207a287f5cba0cb9172';
const SPOONACULAR_BASE = 'https://api.spoonacular.com/recipes/findByIngredients';
const RECIPE_COUNT = 6;

// ===== CACHE (minimize API calls) =====
const recipeCache = new Map();
function getCacheKey(ingredients) {
    return [...ingredients].sort().join(',').toLowerCase();
}

// ===== STATE =====
let trackedIngredients = [];
let cocoModel = null;
let currentDetections = [];
let cameraStream = null;
let animFrameId = null;

// ===== DOM REFS =====
const $ = (sel) => document.querySelector(sel);
const $$ = (sel) => document.querySelectorAll(sel);

const navbar = $('#navbar');
const navLinks = $('#nav-links');
const hamburger = $('#hamburger');
const uploadArea = $('#upload-area');
const fileInput = $('#file-input');
const imagePreview = $('#image-preview');
const previewImg = $('#preview-img');
const removePreview = $('#remove-preview');
const cameraContainer = $('#camera-container');
const video = $('#video');
const canvas = $('#canvas');
const ctx = canvas.getContext('2d');
const btnCapture = $('#btn-capture');
const btnStopCamera = $('#btn-stop-camera');
const btnScanImage = $('#btn-scan-image');
const btnOpenCamera = $('#btn-open-camera');
const detectedList = $('#detected-list');
const emptyMsg = $('#empty-msg');
const manualInput = $('#manual-input');
const btnAddManual = $('#btn-add-manual');
const btnGenerate = $('#btn-generate');
const recipesGrid = $('#recipes-grid');
const recipesEmpty = $('#recipes-empty');
const scanningOverlay = $('#scanning-overlay');
const scanningText = $('#scanning-text');
const toastContainer = $('#toast-container');

// ===== NAVIGATION =====
// Scroll-aware navbar
window.addEventListener('scroll', () => {
    navbar.classList.toggle('scrolled', window.scrollY > 20);
    // Active link highlight
    const sections = $$('section');
    let current = '';
    sections.forEach(s => {
        if (window.scrollY >= s.offsetTop - 120) current = s.id;
    });
    $$('[data-nav]').forEach(a => {
        a.classList.toggle('active', a.getAttribute('href') === '#' + current);
    });
});

// Hamburger
hamburger.addEventListener('click', () => {
    navLinks.classList.toggle('open');
});
navLinks.addEventListener('click', () => {
    navLinks.classList.remove('open');
});

// Smooth scroll helper (global for onclick attrs)
window.scrollTo = function (sel) {
    const el = document.querySelector(sel);
    if (el) el.scrollIntoView({ behavior: 'smooth', block: 'start' });
};

// ===== TOAST =====
function showToast(msg, type = 'info') {
    const t = document.createElement('div');
    t.className = `toast ${type}`;
    t.textContent = msg;
    toastContainer.appendChild(t);
    setTimeout(() => t.remove(), 3000);
}

// ===== INGREDIENT LIST =====
function updateIngredientUI() {
    detectedList.innerHTML = '';
    if (trackedIngredients.length === 0) {
        detectedList.innerHTML = '<p style="opacity:0.5;font-size:0.9rem;" id="empty-msg">No ingredients yet — scan or type below</p>';
        btnGenerate.disabled = true;
        return;
    }
    btnGenerate.disabled = false;
    trackedIngredients.forEach((ing, i) => {
        const tag = document.createElement('div');
        tag.className = 'ingredient-tag';
        tag.innerHTML = `${ing}<span class="remove-tag" data-index="${i}" title="Remove">✕</span>`;
        detectedList.appendChild(tag);
    });
    // Remove handlers
    $$('.remove-tag').forEach(btn => {
        btn.addEventListener('click', () => {
            trackedIngredients.splice(+btn.dataset.index, 1);
            updateIngredientUI();
        });
    });
}

function addIngredient(name) {
    const clean = name.trim().toLowerCase();
    if (!clean || trackedIngredients.includes(clean)) return false;
    trackedIngredients.push(clean);
    updateIngredientUI();
    return true;
}

// Manual add
btnAddManual.addEventListener('click', () => {
    if (addIngredient(manualInput.value)) {
        showToast(`Added "${manualInput.value.trim()}"`, 'success');
        manualInput.value = '';
    }
});
manualInput.addEventListener('keydown', (e) => {
    if (e.key === 'Enter') btnAddManual.click();
});

// ===== FILE UPLOAD =====
uploadArea.addEventListener('click', () => fileInput.click());
uploadArea.addEventListener('dragover', (e) => { e.preventDefault(); uploadArea.classList.add('dragover'); });
uploadArea.addEventListener('dragleave', () => uploadArea.classList.remove('dragover'));
uploadArea.addEventListener('drop', (e) => {
    e.preventDefault();
    uploadArea.classList.remove('dragover');
    if (e.dataTransfer.files.length) handleFile(e.dataTransfer.files[0]);
});
fileInput.addEventListener('change', () => {
    if (fileInput.files.length) handleFile(fileInput.files[0]);
});

function handleFile(file) {
    if (!file.type.startsWith('image/')) {
        showToast('Please upload an image file', 'error');
        return;
    }
    const reader = new FileReader();
    reader.onload = (e) => {
        previewImg.src = e.target.result;
        imagePreview.style.display = 'block';
        uploadArea.style.display = 'none';
        btnScanImage.disabled = false;
    };
    reader.readAsDataURL(file);
}

removePreview.addEventListener('click', () => {
    imagePreview.style.display = 'none';
    uploadArea.style.display = '';
    previewImg.src = '';
    fileInput.value = '';
    btnScanImage.disabled = true;
});

// ===== SCAN UPLOADED IMAGE =====
btnScanImage.addEventListener('click', async () => {
    scanningOverlay.classList.add('active');
    scanningText.textContent = 'Loading AI model…';
    try {
        if (!cocoModel) {
            cocoModel = await cocoSsd.load();
        }
        scanningText.textContent = 'Analyzing ingredients…';
        const predictions = await cocoModel.detect(previewImg);
        let added = 0;
        predictions.forEach(p => {
            if (addIngredient(p.class)) added++;
        });
        if (added > 0) {
            showToast(`Detected ${added} ingredient(s)!`, 'success');
        } else if (predictions.length > 0) {
            showToast('Items already in your list', 'info');
        } else {
            showToast('No items detected — try a clearer photo or add manually', 'error');
        }
    } catch (err) {
        console.error(err);
        showToast('Error scanning image — try again', 'error');
    }
    scanningOverlay.classList.remove('active');
});

// ===== LIVE CAMERA =====
btnOpenCamera.addEventListener('click', async () => {
    try {
        cameraStream = await navigator.mediaDevices.getUserMedia({ video: { facingMode: 'environment' } });
        video.srcObject = cameraStream;
        cameraContainer.style.display = 'block';
        uploadArea.style.display = 'none';
        imagePreview.style.display = 'none';
        btnOpenCamera.style.display = 'none';

        if (!cocoModel) {
            showToast('Loading AI model…', 'info');
            cocoModel = await cocoSsd.load();
            showToast('Model ready — scanning live!', 'success');
        }
        detectFrame();
    } catch (err) {
        console.error(err);
        showToast('Camera access denied — check permissions', 'error');
    }
});

async function detectFrame() {
    if (!cameraStream) return;
    canvas.width = video.videoWidth;
    canvas.height = video.videoHeight;
    const predictions = await cocoModel.detect(video);
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    currentDetections = [];
    predictions.forEach(pred => {
        const [x, y, w, h] = pred.bbox;
        ctx.strokeStyle = '#a4bd38';
        ctx.lineWidth = 3;
        ctx.strokeRect(x, y, w, h);
        ctx.fillStyle = 'rgba(164, 189, 56, 0.85)';
        ctx.font = 'bold 14px Inter, sans-serif';
        const label = `${pred.class} ${Math.round(pred.score * 100)}%`;
        const tw = ctx.measureText(label).width;
        ctx.fillRect(x, y - 22, tw + 12, 22);
        ctx.fillStyle = '#fff';
        ctx.fillText(label, x + 6, y - 6);
        currentDetections.push(pred.class);
    });
    animFrameId = requestAnimationFrame(detectFrame);
}

btnCapture.addEventListener('click', () => {
    let added = 0;
    currentDetections.forEach(item => {
        if (addIngredient(item)) added++;
    });
    if (added > 0) showToast(`Added ${added} item(s)`, 'success');
    else showToast('No new items detected', 'info');
});

btnStopCamera.addEventListener('click', stopCamera);

function stopCamera() {
    if (animFrameId) cancelAnimationFrame(animFrameId);
    if (cameraStream) {
        cameraStream.getTracks().forEach(t => t.stop());
        cameraStream = null;
    }
    cameraContainer.style.display = 'none';
    uploadArea.style.display = '';
    btnOpenCamera.style.display = '';
}

// ===== GENERATE RECIPES (Spoonacular) =====
btnGenerate.addEventListener('click', async () => {
    if (trackedIngredients.length === 0) return;

    const cacheKey = getCacheKey(trackedIngredients);
    // Check cache first
    if (recipeCache.has(cacheKey)) {
        renderRecipes(recipeCache.get(cacheKey));
        showToast('Recipes loaded from cache ⚡', 'success');
        document.querySelector('#recipes').scrollIntoView({ behavior: 'smooth' });
        return;
    }

    btnGenerate.disabled = true;
    btnGenerate.textContent = '⏳ Fetching recipes…';

    try {
        const ingredientStr = trackedIngredients.join(',');
        const url = `${SPOONACULAR_BASE}?ingredients=${encodeURIComponent(ingredientStr)}&number=${RECIPE_COUNT}&ranking=1&ignorePantry=true&apiKey=${SPOONACULAR_API_KEY}`;
        const resp = await fetch(url);
        if (!resp.ok) {
            if (resp.status === 402) throw new Error('API rate limit hit — try again later');
            throw new Error(`API error: ${resp.status}`);
        }
        const data = await resp.json();
        // Cache the result
        recipeCache.set(cacheKey, data);
        renderRecipes(data);
        if (data.length > 0) {
            showToast(`Found ${data.length} recipes!`, 'success');
        } else {
            showToast('No recipes found — try different ingredients', 'error');
        }
        document.querySelector('#recipes').scrollIntoView({ behavior: 'smooth' });
    } catch (err) {
        console.error(err);
        showToast(err.message || 'Error fetching recipes', 'error');
    }

    btnGenerate.disabled = false;
    btnGenerate.textContent = '🍳 Generate Recipes';
});

function renderRecipes(recipes) {
    if (!recipes || recipes.length === 0) {
        recipesGrid.style.display = 'none';
        recipesEmpty.style.display = '';
        return;
    }
    recipesEmpty.style.display = 'none';
    recipesGrid.style.display = '';
    recipesGrid.innerHTML = '';

    recipes.forEach(recipe => {
        const usedCount = recipe.usedIngredientCount || 0;
        const missedCount = recipe.missedIngredientCount || 0;
        const totalMatch = usedCount + missedCount;
        const matchPct = totalMatch > 0 ? Math.round((usedCount / totalMatch) * 100) : 0;

        const usedTags = (recipe.usedIngredients || [])
            .map(i => `<span>${i.name}</span>`).join('');
        const missedTags = (recipe.missedIngredients || [])
            .slice(0, 3)
            .map(i => `<span>${i.name}</span>`).join('');

        const card = document.createElement('div');
        card.className = 'recipe-card';
        card.innerHTML = `
            <div class="recipe-card-image">
                <img src="${recipe.image}" alt="${recipe.title}" loading="lazy">
                <div class="recipe-match-badge">${matchPct}% match</div>
            </div>
            <div class="recipe-card-body">
                <h3>${recipe.title}</h3>
                ${usedTags ? `<div class="recipe-used-ingredients">${usedTags}</div>` : ''}
                ${missedTags ? `<div class="recipe-missed-ingredients">${missedTags}</div>` : ''}
                <div class="recipe-meta">
                    <span class="recipe-likes">❤️ ${recipe.likes || 0} likes</span>
                    <a href="https://spoonacular.com/recipes/${recipe.title.replace(/\s+/g, '-').toLowerCase()}-${recipe.id}" target="_blank" rel="noopener" class="recipe-link">View Recipe →</a>
                </div>
            </div>
        `;
        recipesGrid.appendChild(card);
    });
}

// ===== INIT =====
updateIngredientUI();
