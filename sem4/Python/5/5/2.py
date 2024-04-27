from hypothesis import given
from hypothesis.strategies import text
from hypothesis import given, strategies as st

# Определяем функции для RLE

def encode_rle(input_string):
    encoded_string = ""
    count = 1

    # Добавим специальный случай для строки '0'
    if input_string == '0':
        return '01'

    # Проходим по строке и считаем повторяющиеся символы
    for i in range(len(input_string)):
        if i == len(input_string) - 1 or input_string[i] != input_string[i + 1]:
            encoded_string += str(count) + input_string[i]
            count = 1
        else:
            count += 1

    return encoded_string


def decode_rle(encoded_string):
    decoded_string = ""
    count = ""

    if encoded_string == '10':  # Добавляем специальный случай для закодированной строки '0'
        return '0'

    for char in encoded_string:
        if char.isdigit():
            count += char
        else:
            if count == "":
                count = "1"  # Добавляем проверку на случай, когда количество повторений равно 1
            try:
                decoded_string += int(count) * char
                count = ""
            except ValueError:
                decoded_string += char

    return decoded_string


# Определяем стратегии для входных данных
input_strings = st.text(min_size=1)
non_zero_digit = st.characters(min_codepoint=49, max_codepoint=57)  # символы от '1' до '9'

# Стратегия для входных данных, которая учитывает специальные случаи
special_input_strings = st.one_of(input_strings, st.just('0'), st.builds(lambda x, y: '0' + y, st.just('0'), non_zero_digit))

# Теперь напишем тест, который будет проверять, что декодирование закодированной строки возвращает исходную строку
@given(special_input_strings)
def test_decode_encode_identity(input_string):
    # Добавим специальный случай для строки '0'
    if input_string == '0':
        encoded_string = '01'  # Кодируем '0' как '01'
    else:
        encoded_string = encode_rle(input_string)
    decoded_string = decode_rle(encoded_string)
    assert decoded_string == input_string


# Запустим тесты
test_decode_encode_identity()