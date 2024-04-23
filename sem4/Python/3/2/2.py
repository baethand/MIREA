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

# Перебор каждого спрайта
for sprite in range(ROWS * COLS):
    X = sprite // ROWS  # Вычисление индекса столбца текущего спрайта
    Y = sprite % ROWS  # Вычисление индекса строки текущего спрайта
    mid = sprite_width // 2 + sprite_width % 2  # Вычисление середины ширины спрайта

    # Перебор каждого пикселя в левой половине спрайта
    for col in range(1, mid):
        for row in range(1, sprite_height - 1):
            # Присвоение случайного значения RGB пикселю
            x[Y * sprite_height + row, X * sprite_width + col] += np.array([np.random.randint(2)] * 3)

        # Зеркальное отображение левой половины спрайта на правую
        x[:, X * sprite_width + (sprite_width - col - 1)] = x[:, X * sprite_width + col]

plt.imshow(x)  # Отображение спрайтов
plt.show()  # Показ графика
