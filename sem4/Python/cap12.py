import struct

# Определение форматов для каждой структуры
format_A = ">q I f i f I bb"


format_B = ">Q b H H B 4s I H"
format_G = ">B i b I"

# Двоичные данные
binary_data = (b'PVYIL\x81Fs\xf4\x88iW\x89g\x16\xe3!\xd1\xa0BB\xf2\x00]\x00\x84\xdboeka\x00'
               b'\x00\x00\x8e\x00\x03\x00\x95<}\rG\xa7c\xcc\xc8>T\x9a\x8c\x9c\xd0\xe6\x117'
               b'\xadW\x15q\xd4\x1d\xcaJ\\\xb7\x8fFC\xd8\xb2>\x85\xae\xdapwJ^tyj\x80\xbd'
               b'\xb3\xde\xbczQ\xfc\x8a\xc2\xc9>\x84\x9b_\xd8\x1bJD\xb9\xcc\x00\x00\x00?\x00'
               b"\x00\x00E\x00\x00\x00K\x00\x00\x00Q\x00\x00\x00W\x9e[w\x82\xf3'\x0fh\\"
               b'\xd3\xb6\x95\x8aB\x90\xa1\xf0\x0b\xc5\x00\x04\x00\x00\x00\x8aB\xd3\x08\xa7'
               b'\xc1\xed\xa0')

# Разбор данных
A1, B1, C1, A3, A4, A5, G1, A7_1, A7_2 = struct.unpack(format_A, binary_data[:30])
B2, C2, C3, D1_1, D2_1, D1_2, D2_2, D1_3, D2_3, D1_4, D2_4, D1_5, D2_5, C5, C6 = struct.unpack(">bHfIIIIIIIIiQ", binary_data[46:94])
E1, E2, E3 = struct.unpack(">hhh", binary_data[94:100])
F1_1, F1_2, F1_3, F1_4, F2 = struct.unpack(">biiiiB", binary_data[100:116])
G2, G3, G4 = struct.unpack(">ibI", binary_data[116:126])

# Преобразование массива uint16 в список
B8 = []
for i in range(126, 140, 2):
    B8.append(struct.unpack(">H", binary_data[i:i+2])[0])

# Преобразование массива int8 в список
A7 = struct.unpack("bb", binary_data[-2:])

# Вывод результатов
print({
    'A1': A1,
    'A2': {
        'B1': B1,
        'B2': B2,
        'B3': {
            'C1': C1,
            'C2': C2,
            'C3': C3,
            'C4': [{'D1': D1_1, 'D2': D2_1}, {'D1': D1_2, 'D2': D2_2},
                    {'D1': D1_3, 'D2': D2_3}, {'D1': D1_4, 'D2': D2_4},
                    {'D1': D1_5, 'D2': D2_5}],
            'C5': C5,
            'C6': C6
        },
        'B4': {'E1': E1, 'E2': E2, 'E3': E3},
        'B5': B5,
        'B6': binary_data[100:104].decode(),
        'B7': {'F1': [F1_1, F1_2, F1_3, F1_4], 'F2': F2},
        'B8': B8
    },
    'A3': A3,
    'A4': A4,
    'A5': A5,
    'A6': {'G1': G1, 'G2': G2, 'G3': G3, 'G4': G4},
    'A7': A7
})
