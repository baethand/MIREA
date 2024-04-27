import numpy as np
import matplotlib.pyplot as plt

PAIRS = "..LEXEGEZACEBISO" + "USESARMAINDIREA." + "ERATENBERALAVETI" + "EDORQUANTEISRION"


class SeedType:
    def __init__(self, w0, w1, w2):
        self.w0 = w0
        self.w1 = w1
        self.w2 = w2


class PlanetarySystem:
    def __init__(self):
        self.x_coordinate = 0
        self.y_coordinate = 0
        self.economy_level = 0
        self.government_type = 0
        self.tech_level = 0
        self.population = 0
        self.productivity = 0
        self.radius = 0
        self.seed = SeedType(0, 0, 0)
        self.name = [''] * 12


def tweak_seed(seed):
    temp = seed.w0 + seed.w1 + seed.w2
    seed.w0 = seed.w1
    seed.w1 = seed.w2
    seed.w2 = temp


def make_system(seed):
    planetary_system = PlanetarySystem()
    set_coordinates(planetary_system, seed)
    set_economy_and_government(planetary_system, seed)
    calculate_population_and_productivity(planetary_system)
    set_radius(planetary_system, seed)
    set_seed(planetary_system, seed)
    generate_name(planetary_system, seed)
    return planetary_system


def set_coordinates(planetary_system, seed):
    planetary_system.x_coordinate = (seed.w1 >> 8) % 256
    planetary_system.y_coordinate = (seed.w0 >> 8) % 256


def set_economy_and_government(planetary_system, seed):
    planetary_system.government_type = (seed.w1 >> 3) & 7
    planetary_system.economy_level = (seed.w0 >> 8) & 7
    if planetary_system.government_type <= 1:
        planetary_system.economy_level |= 2
    planetary_system.tech_level = ((seed.w1 >> 8) & 3) + (planetary_system.economy_level ^ 7)
    planetary_system.tech_level += planetary_system.government_type >> 1
    if planetary_system.government_type & 1 == 1:
        planetary_system.tech_level += 1


def calculate_population_and_productivity(planetary_system):
    planetary_system.population = 4 * planetary_system.tech_level + planetary_system.economy_level
    planetary_system.population += planetary_system.government_type + 1
    planetary_system.productivity = ((planetary_system.economy_level ^ 7) + 3) * (planetary_system.government_type + 4)
    planetary_system.productivity *= planetary_system.population * 8


def set_radius(planetary_system, seed):
    planetary_system.radius = 256 * (((seed.w2 >> 8) & 15) + 11) + planetary_system.x_coordinate


def set_seed(planetary_system, seed):
    planetary_system.seed.a = seed.w1 & 0xFF
    planetary_system.seed.b = seed.w1 >> 8
    planetary_system.seed.c = seed.w2 & 0xFF
    planetary_system.seed.d = seed.w2 >> 8


def generate_name(planetary_system, seed):
    long_name_flag = seed.w0 & 64
    pair1, pair2, pair3, pair4 = generate_pairs(seed)
    planetary_system.name[0] = PAIRS[pair1]
    planetary_system.name[1] = PAIRS[pair1 + 1]
    planetary_system.name[2] = PAIRS[pair2]
    planetary_system.name[3] = PAIRS[pair2 + 1]
    planetary_system.name[4] = PAIRS[pair3]
    planetary_system.name[5] = PAIRS[pair3 + 1]
    if long_name_flag:
        planetary_system.name[6] = PAIRS[pair4]
        planetary_system.name[7] = PAIRS[pair4 + 1]
        planetary_system.name[8] = ''
    else:
        planetary_system.name[6] = ''
    planetary_system.name = ''.join(planetary_system.name).replace('.', '')


def generate_pairs(seed):
    pairs = []
    for _ in range(4):
        pairs.append(2 * ((seed.w2 >> 8) & 31))
        tweak_seed(seed)
    return pairs

seed = SeedType(0x5A4A, 0x0248, 0xB753)
systems = []
for i in range(256):
    systems.append(make_system(seed))
x = [sys.x_coordinate for sys in systems]
y = [sys.y_coordinate for sys in systems]
names = [sys.name for sys in systems]
plt.figure(figsize=(20, 10))
plt.gca().invert_yaxis()
plt.scatter(x, y, c='blue', alpha=0.5)
plt.rcParams.update({'font.size': 7})
for i in range(len(names)):
    plt.text(x[i], y[i] - 3, names[i], va='bottom', ha='center', color='white')
plt.grid(False)
plt.gca().set_facecolor('black')
plt.show()