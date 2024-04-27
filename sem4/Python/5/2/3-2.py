import coverage
import sys

# Создаем объект coverage
cov = coverage.Coverage()

def divide(a, b):
    if isinstance(a, str) or isinstance(b, str):
        return "Error: both inputs should be numbers"
    elif b == 0:
        return "Error: division by zero"
    else:
        return a / b

def main():
    x = input("Enter the first number: ")  # Вводим строку вместо числа
    y = int(input("Enter the second number: "))
    result = divide(x, y)
    print("Result:", result)

if __name__ == "__main__":
    # Запускаем программу с coverage
    cov.start()
    main()
    cov.stop()

    # Получаем статистику по покрытию операторов
    cov.report(file=sys.stdout)

    # Получаем статистику по покрытию ветвей
    cov.report(show_missing=False)

    # Находим случай, когда покрытие ветвей отличается от покрытия операторов
    cov.html_report()

    # Выводим статистику о покрытии в HTML-представлении с демонстрацией покрытия по строкам программы
    cov.html_report()

def divide(a, b):
    if isinstance(a, str) or isinstance(b, str):
        return "Error: both inputs should be numbers"
    elif b == 0:
        return "Error: division by zero"
    else:
        return a / b


def main():
    x = input("Enter the first number: ")  # Вводим строку вместо числа
    y = int(input("Enter the second number: "))
    result = divide(x, y)
    print("Result:", result)