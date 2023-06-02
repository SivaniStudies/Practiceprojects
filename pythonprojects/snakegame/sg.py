import pygame
import random

# Initialize the game
pygame.init()

# Set up the display
width, height = 640, 480
window = pygame.display.set_mode((width, height))
pygame.display.set_caption("Snake Game")

# Define colors
bg = pygame.Color(255, 192, 203)  # Light Pink
white = pygame.Color(255, 255, 255)
black = pygame.Color(0, 0, 0)
skc = pygame.Color(255, 179, 71)  # Orange
red = pygame.Color(255, 0, 0)

# Define constants
SEGMENT_SIZE = 10
BOX_WIDTH, BOX_HEIGHT = 200, 80
SCORE_BOX_POSITION = (width - BOX_WIDTH - 12, 8)
SCORE_TEXT_POSITION = (width - BOX_WIDTH + 20, 30)
LIVES_TEXT_POSITION = (width - BOX_WIDTH + 20, 60)

# Define the Snake class
class Snake:
    def __init__(self):
        self.body = [(200, 50), (190, 50), (180, 50)]
        self.direction = "RIGHT"
        self.next_direction = self.direction

    def move(self):
        if self.next_direction == "UP" and self.direction != "DOWN":
            self.direction = "UP"
        if self.next_direction == "DOWN" and self.direction != "UP":
            self.direction = "DOWN"
        if self.next_direction == "LEFT" and self.direction != "RIGHT":
            self.direction = "LEFT"
        if self.next_direction == "RIGHT" and self.direction != "LEFT":
            self.direction = "RIGHT"

        if self.direction == "UP":
            new_head = (self.body[0][0], self.body[0][1] - SEGMENT_SIZE)
        if self.direction == "DOWN":
            new_head = (self.body[0][0], self.body[0][1] + SEGMENT_SIZE)
        if self.direction == "LEFT":
            new_head = (self.body[0][0] - SEGMENT_SIZE, self.body[0][1])
        if self.direction == "RIGHT":
            new_head = (self.body[0][0] + SEGMENT_SIZE, self.body[0][1])

        self.body.insert(0, new_head)
        self.body.pop()

    def change_direction(self, direction):
        self.next_direction = direction

    def draw(self, surface):
        for segment in self.body:
            pygame.draw.rect(surface, skc, pygame.Rect(segment[0], segment[1], SEGMENT_SIZE, SEGMENT_SIZE))
            pygame.draw.rect(surface, black, pygame.Rect(segment[0], segment[1], SEGMENT_SIZE, SEGMENT_SIZE), 1)

    def is_colliding_with_box(self):
        box_rect = pygame.Rect(SCORE_BOX_POSITION[0], SCORE_BOX_POSITION[1], BOX_WIDTH, BOX_HEIGHT)
        for segment in self.body:
            if box_rect.collidepoint(segment):
                return True
        return False


# Define the Fruit class
# Define the Fruit class
class Fruit:
    def __init__(self):
        self.position = (0, 0)
        self.spawn()

    def spawn(self):
        while True:
            new_position = (
                random.randint(1, (width // SEGMENT_SIZE) - 1) * SEGMENT_SIZE,
                random.randint(1, (height // SEGMENT_SIZE) - 1) * SEGMENT_SIZE,
            )
            fruit_rect = pygame.Rect(new_position[0], new_position[1], SEGMENT_SIZE, SEGMENT_SIZE)
            score_box_rect = pygame.Rect(SCORE_BOX_POSITION[0], SCORE_BOX_POSITION[1], BOX_WIDTH, BOX_HEIGHT)
            if not fruit_rect.colliderect(score_box_rect):
                self.position = new_position
                break

    def draw(self, surface):
        pygame.draw.rect(surface, red, pygame.Rect(self.position[0], self.position[1], SEGMENT_SIZE, SEGMENT_SIZE))


# Initialize the Snake, Fruit, Score, and Lives
snake = Snake()
fruit = Fruit()
score = 0
lives = 3

# Set up the game clock
clock = pygame.time.Clock()

# Set up the font for displaying the score and lives
font = pygame.font.Font(None, 36)

# Set up the font for the score and lives box
box_font = pygame.font.Font(None, 24)

# Create a surface for the score and lives box
box_surface = pygame.Surface((BOX_WIDTH, BOX_HEIGHT))
box_surface.fill(white)

# Game loop
while True:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            pygame.quit()
            exit(0)
        elif event.type == pygame.KEYDOWN:
            if event.key == pygame.K_UP:
                snake.change_direction("UP")
            elif event.key == pygame.K_DOWN:
                snake.change_direction("DOWN")
            elif event.key == pygame.K_LEFT:
                snake.change_direction("LEFT")
            elif event.key == pygame.K_RIGHT:
                snake.change_direction("RIGHT")

    snake.move()

    if snake.body[0] == fruit.position:
        snake.body.append((0, 0))
        fruit.spawn()
        score += 1

    if (
        snake.body[0][0] < 10
        or snake.body[0][0] >= width - 10
        or snake.body[0][1] < 10
        or snake.body[0][1] >= height - 10
        or snake.is_colliding_with_box()
    ):
        lives -= 1
        if lives == 0:
            pygame.quit()
            exit(0)
        else:
            snake = Snake()  # Reset the snake position
            score = 0  # Reset the score
            fruit.spawn()  # Respawn the fruit

    for segment in snake.body[1:]:
        if segment == snake.body[0]:
            lives -= 1
            if lives == 0:
                pygame.quit()
                exit(0)
            else:
                snake = Snake()  # Reset the snake position
                score = 0  # Reset the score
                fruit.spawn()  # Respawn the fruit

    window.fill(bg)

    # Draw the black border around the game window
    pygame.draw.rect(window, black, pygame.Rect(0, 0, width, height), 10)

    # Draw the score and lives box
    window.blit(box_surface, SCORE_BOX_POSITION)

    # Draw the border around the score and lives box
    pygame.draw.rect(window, black, pygame.Rect(SCORE_BOX_POSITION[0], SCORE_BOX_POSITION[1], BOX_WIDTH + 4, BOX_HEIGHT + 4), 2)

    # Display the score and lives on the screen
    score_text = font.render("Score: " + str(score), True, black)
    window.blit(score_text, SCORE_TEXT_POSITION)
    lives_text = font.render("Lives: " + str(lives), True, black)
    window.blit(lives_text, LIVES_TEXT_POSITION)

    window.blit(lives_text, (width - BOX_WIDTH + 20, 60))


    snake.draw(window)
    fruit.draw(window)

    pygame.display.update()
    clock.tick(15)
