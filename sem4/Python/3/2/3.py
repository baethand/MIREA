import matplotlib.pyplot as plt  # Библиотека для построения графиков
import numpy as np  # Библиотека для работы с массивами

WIDTH = 5  # Ширина спрайта
HEIGHT = 5  # Высота спрайта
ROWS = 10  # Количество строк спрайтов
COLS = 20  # Количество столбцов спрайтов
PADDING = 2  # Отступ вокруг каждого спрайта

sprite_width = WIDTH + PADDING  # Общая ширина спрайта с учетом отступа
sprite_height = HEIGHT + PADDING  # Общая высота спрайта с учетом отступа

# Создание трехмерного массива numpy для хранения спрайтов. Последнее измерение предназначено для значений RGB.
x = np.zeros((sprite_height * ROWS, sprite_width * COLS, 3))
print(x.shape)  # Вывод формы массива

# Палитра цветов
colors = [
    "#1D2B53", "#7E2553", "#008751", "#AB5236", "#5F574F", "#C2C3C7", "#FFF1E8", "#FF004D",
    "#FFA300", "#FFEC27", "#00E436", "#29ADFF", "#83769C", "#FF77A8", "#FFCCAA"
]

# Перебор каждого спрайта
for sprite in range(ROWS * COLS):
    X = sprite // ROWS  # Вычисление индекса столбца текущего спрайта
    Y = sprite % ROWS  # Вычисление индекса строки текущего спрайта
    mid = sprite_width // 2 + sprite_width % 2  # Вычисление середины ширины спрайта

    # Генерация нескольких случайных цветов для спрайта
    colors_sprite = np.array([np.array(list(map(lambda x: int(x, 16) / 255, colors[np.random.randint(len(colors))][1:].upper()))) for _ in range(16)])

    # Перебор каждого пикселя в левой половине спрайта
    for col in range(1, mid):
        for row in range(1, sprite_height - 1):
            # Выбор случайного цвета для пикселя
            color = colors_sprite[np.random.randint(0, colors_sprite.shape[0])]

            # Увеличение яркости цвета
            color *= 8

            # Присвоение случайного значения RGB пикселю, умноженного на цвет спрайта
            rgb = np.array([np.random.randint(2)] * 3) * color[:3]
            x[Y * sprite_height + row, X * sprite_width + col, :] = rgb

        # Зеркальное отображение левой половины спрайта на правую
        x[:, X * sprite_width + (sprite_width - col - 1), :] = x[:, X * sprite_width + col, :]

plt.imshow(x)  # Отображение спрайтов
plt.show()  # Показ графика
