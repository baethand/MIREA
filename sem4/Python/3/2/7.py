from PIL import Image

def get_image_rgb_values(image_path):
    # Открываем изображение
    img = Image.open(image_path)
    # Преобразуем изображение в RGB
    img_rgb = img.convert('RGB')
    # Получаем размеры изображения
    width, height = img_rgb.size
    # Создаем двумерный массив для хранения RGB-значений
    rgb_values = []
    for y in range(height):
        row = []
        for x in range(width):
            r, g, b = img_rgb.getpixel((x, y))
            row.append((r, g, b))
        rgb_values.append(row)
    return rgb_values

# Путь к вашему изображению
image_path = "C:\\3d\\ddccb51c95b3a2f9d68ed543ff4c792f.jpg"
# Получаем RGB-значения изображения
rgb_values = get_image_rgb_values(image_path)

# Выводим RGB-значения
for row in rgb_values:
    print(row)