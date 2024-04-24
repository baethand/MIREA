import re

def main(input_string):
    # Используем регулярное выражение для поиска совпадений
    pattern = r'<section>\s*set\s+(\w+)\s*:=\s*\[\s*([^]]*?)\s*\]\.'
    matches = re.findall(pattern, input_string)
    
    parsed_results = []
    
    # Проходим по всем найденным совпадениям
    for match in matches:
        name = match[0]  # Получаем имя переменной
        values = match[1].split(';')  # Разделяем значения
        
        # Удаляем лишние пробелы и пробелы в начале/конце строк значений
        values = [value.strip() for value in values if value.strip()]
        
        parsed_results.append((name, values))
    
    return parsed_results

# Входная строка
input_string = '\\begin <section>set quveza_495 := [ cemaat ; bequve ;orerso ;raveti\n]. </section>; <section> set orer_648:= [ tima_476 ; uslaqu].\n</section>;\\end'

# Вызываем функцию парсинга
parsed_results = parse_input_string(input_string)

# Выводим результат
print(parsed_results)
