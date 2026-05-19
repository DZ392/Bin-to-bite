# I - Import and Initialize
import pygame, mySprites
pygame.init()
            
def main():
    '''This function defines the 'mainline logic' for our game.'''  
    # Display
    screen = pygame.display.set_mode((750, 500))    
    pygame.display.set_caption("Culminating Game")
 	
    # Entities
    background = pygame.Surface(screen.get_size())
    background = pygame.image.load('bg.png')
    background = pygame.transform.scale(background, (750, 500))
    screen.blit(background, (0, 0))
    count = 0
    start = pygame.time.get_ticks()
    

 	
    # Instantiate our 3 custom sprites and create an OrderedUpdates Sprite Group
    
    ground = mySprites.Grass(screen, 75, 475, 150, 50), mySprites.Grass(screen, 225, 475, 150, 50),mySprites.Grass(screen, 375, 475, 150, 50),mySprites.Grass(screen, 525, 475, 150, 50),mySprites.Grass(screen, 675, 475, 150, 50),
    
    
    #2nd and 4th one blink
    player = mySprites.Player(screen)
    cherries = []
    for i in range(10):
      cherry = mySprites.Cherry(screen)
      cherries.append(cherry)
    bombs = []
    for i in range(3):
      bomb = mySprites.Bomb(screen)
      bombs.append(bomb)
    cherriess = pygame.sprite.OrderedUpdates(cherries)
    bombss = pygame.sprite.OrderedUpdates(bombs)
    allSprites = pygame.sprite.OrderedUpdates(ground,player, cherries, bombs)
 	
    # ACTION
 	
    # Assign
    clock = pygame.time.Clock()
    keepGoing = True

    # Loop
    while keepGoing:
        # Time
        clock.tick(30)

        now = pygame.time.get_ticks()
        if now - start >= 10000:
          keepGoing = False

        for event in pygame.event.get():
            if event.type == pygame.QUIT:
              keepGoing = False
            
            keys = pygame.key.get_pressed()

            if keys[pygame.K_w]:
                  player.go_up()

            elif keys[pygame.K_a]:
                  player.go_left()

            elif keys[pygame.K_d]:
                  player.go_right()
            else:
                player.stop()
        # Events
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                keepGoing = False

        if pygame.sprite.spritecollide(player, cherriess, True):
          cherry.kill()
          count = count+1
        if pygame.sprite.spritecollide(player, bombss, True):
          keepGoing = False

        # Refresh screen
        allSprites.clear(screen, background)
        allSprites.update(screen)
        allSprites.draw(screen)             	
        pygame.display.flip()
    # Close the game window
    pygame.quit()   
    print("Your score is: " + str(count)) 
     	
# Call the main function
main()