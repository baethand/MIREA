def add_numbers(a, b):
    return a + b


# Функция для ввода чисел с обработкой исключений
def input_number(prompt):
    while True:
        try:
            num = float(input(prompt))
            return num
        except ValueError:
            print("Ошибка: Введите число.")


# Функция для тестирования функции сложения чисел
def test_add_numbers():
    test_cases = [
        (2, 3, 5),
        (-1, 1, 0),
        (0, 0, 0),
        (1.5, 2.5, 4),
        ("1", "2", "12"),
        ("one", 2, None),
        (1, "two", None),
    ]

    for a, b, expected in test_cases:
        try:
            result = add_numbers(a, b)
            print(f"Сумма чисел {a} и {b}:", result)
            assert result == expected, f"Ошибка: Ожидаемый результат: {expected}, получено: {result}"
        except TypeError:
            assert expected is None, "Ошибка: Функция не должна завершиться успешно для неправильных типов ввода."
            print("Тест успешно завершен: ввод неверного типа обработан правильно.")


# Основная функция программы
def main():
    # Выполнение макетного теста
    print("Макетный тест:")
    test_add_numbers()

    # Ввод пользователя
    print("\nВвод пользователя:")
    while True:
        num1 = input_number("Введите первое число: ")
        num2 = input_number("Введите второе число: ")

        result = add_numbers(num1, num2)
        print("Сумма чисел:", result)

        choice = input("Хотите ли вы продолжить? (да/нет): ")
        if choice.lower() != 'да':
            break


# Вызов основной функции
if __name__ == "__main__":
    main()