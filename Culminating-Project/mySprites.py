import pygame
import random

class Grass(pygame.sprite.Sprite):

    def __init__(self, screen, xpos, ypos, leng, wid):
        # Call the parent __init__() method
        pygame.sprite.Sprite.__init__(self)
        # Set the image and rect attributes for the bricks
        self.image = pygame.image.load("grass.png")
        self.image = pygame.transform.scale(self.image, (leng, wid))
        self.rect = self.image.get_rect()
        self.rect.centerx = xpos
        self.rect.centery = ypos

class Player(pygame.sprite.Sprite):

    def __init__(self, screen):
        # Call the parent __init__() method
        pygame.sprite.Sprite.__init__(self)
        # Set the image and rect attributes for the bricks
        self.image = pygame.image.load("player.png")
        self.image = pygame.transform.scale(self.image, (50,50))
        self.image = pygame.transform.rotate(self.image, 90)
        self.rect = self.image.get_rect()
        self.rect.centerx = 25
        self.rect.centery = 425

        #movement
        self.direction = pygame.math.Vector2(0, 0)
        self.gravity = 1
        self.jump_speed = -16


    def go_right(self):
      self.direction.x = 20

    def go_left(self):
      self.direction.x = -20

    def go_up(self):
      self.direction.y = -10
      
    def stop(self):
      self.direction.x = 0
      
    def apply_gravity(self):
      self.direction.y += self.gravity
      self.rect.y += self.direction.y

    def update(self, screen):
      self.rect.x += self.direction.x
      self.apply_gravity()

      if self.rect.left < 0:
        self.rect.left = 0
      if self.rect.right > screen.get_width():
        self.rect.right = screen.get_width()
      if self.rect.top <= 350:
        self.rect.top = 350
      if self.rect.bottom >= screen.get_height()-50:
        self.rect.bottom = screen.get_height() -50

class Cherry(pygame.sprite.Sprite):
    '''A simple Sprite subclass to represent static Brick sprites.'''
    def __init__(self, screen):
        # Call the parent __init__() method
        pygame.sprite.Sprite.__init__(self)
        # Set the image and rect attributes for the bricks
        self.image = pygame.image.load("cherry.png")
        self.image = pygame.transform.scale(self.image, (40, 40))
        self.rect = self.image.get_rect()
        self.rect.centerx = random.randrange(0, screen.get_width())
        self.rect.centery = random.randrange(-300,-100)
        self.speed = random.randrange(3,10)
        
    def update(self, screen):
      self.rect.centery += self.speed

class Bomb(pygame.sprite.Sprite):
    '''A simple Sprite subclass to represent static Brick sprites.'''
    def __init__(self, screen):
        # Call the parent __init__() method
        pygame.sprite.Sprite.__init__(self)
        # Set the image and rect attributes for the bricks
        self.image = pygame.image.load("bomb.png")
        self.image = pygame.transform.scale(self.image, (30, 30))
        self.rect = self.image.get_rect()
        self.rect.centerx = random.randrange(0, screen.get_width())
        self.rect.centery = random.randrange(-300,-100)
        self.speed = random.randrange(3,10)
        
    def update(self, screen):
      self.rect.centery += self.speed