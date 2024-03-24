import pygame
import random

class Chip8Emulator:
    def __init__(self):
        self.memory = [0] * 4096
        self.V = [0] * 16
        self.I = 0
        self.pc = 0x200
        self.stack = []
        self.delay_timer = 0
        self.sound_timer = 0
        self.keypad = [0] * 16
        self.screen = [[0] * 64 for _ in range(32)]
        self.opcode = 0
        self.draw_flag = False  # Set to True when screen needs to be redrawn

        # Initialize fonts in memory
        self.memory[:80] = [
            0xF0, 0x90, 0x90, 0x90, 0xF0,  # 0
            0x20, 0x60, 0x20, 0x20, 0x70,  # 1
            0xF0, 0x10, 0xF0, 0x80, 0xF0,  # 2
            0xF0, 0x10, 0xF0, 0x10, 0xF0,  # 3
            0x90, 0x90, 0xF0, 0x10, 0x10,  # 4
            0xF0, 0x80, 0xF0, 0x10, 0xF0,  # 5
            0xF0, 0x80, 0xF0, 0x90, 0xF0,  # 6
            0xF0, 0x10, 0x20, 0x40, 0x40,  # 7
            0xF0, 0x90, 0xF0, 0x90, 0xF0,  # 8
            0xF0, 0x90, 0xF0, 0x10, 0xF0,  # 9
            0xF0, 0x90, 0xF0, 0x90, 0x90,  # A
            0xE0, 0x90, 0xE0, 0x90, 0xE0,  # B
            0xF0, 0x80, 0x80, 0x80, 0xF0,  # C
            0xE0, 0x90, 0x90, 0x90, 0xE0,  # D
            0xF0, 0x80, 0xF0, 0x80, 0xF0,  # E
            0xF0, 0x80, 0xF0, 0x80, 0x80   # F
        ]

    def load_rom(self, filename):
        with open(filename, 'rb') as file:
            rom = file.read()
            for i, byte in enumerate(rom):
                self.memory[0x200 + i] = byte

    def emulate_cycle(self):
        # Fetch opcode
        self.opcode = (self.memory[self.pc] << 8) | self.memory[self.pc + 1]

        # Update timers
        if self.delay_timer > 0:
            self.delay_timer -= 1
        if self.sound_timer > 0:
            if self.sound_timer == 1:
                # Beep
                pass
            self.sound_timer -= 1

        def update_screen(self):
            screen = pygame.display.set_mode((64 * 10, 32 * 10))
            for y in range(32):
                for x in range(64):
                    if self.screen[y][x] == 1:
                        pygame.draw.rect(screen, (255, 255, 255), (x * 10, y * 10, 10, 10))
                    else:
                        pygame.draw.rect(screen, (0, 0, 0), (x * 10, y * 10, 10, 10))
            pygame.display.flip()


    def run(self):
        # Initialize display
        pygame.init()
        screen = pygame.display.set_mode((640, 320))

        clock = pygame.time.Clock()

        while True:
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    pygame.quit()
                    quit()

            self.emulate_cycle()
            if self.draw_flag:
                self.update_screen()
                self.draw_flag = False

            # Limit the speed of the emulation to ~500 Hz
            clock.tick(60)

if __name__ == '__main__':
    emulator = Chip8Emulator()
    emulator.load_rom('tetris.rom') 
    emulator.run()
