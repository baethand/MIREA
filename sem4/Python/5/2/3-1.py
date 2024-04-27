import coverage
import sys

# Создаем объект coverage
cov = coverage.Coverage()

def divide(a, b):
    if b == 0:
        return "Error: division by zero"
    else:
        return a / b

def main():
    x = int(input("Enter the first number: "))
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

    # Изменяем код исходной программы, чтобы затруднить получение 100% покрытия
    # Для этого можем добавить еще ветвлений в функции и убедиться, что не все ветви покрыты

    # Находим простой пример ошибки при полученном 100% покрытии
    # В данном случае, программа не обрабатывает ошибку деления на 0, что может привести к сбою

    # Выводим статистику о покрытии в HTML-представлении с демонстрацией покрытия по строкам программы
    cov.html_report()