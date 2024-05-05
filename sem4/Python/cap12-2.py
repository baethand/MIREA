import struct


def read_uint8(data, offset):
    return struct.unpack_from(">B", data, offset)[0], offset + 1


def read_int64(data, offset):
    return struct.unpack_from(">q", data, offset)[0], offset + 8


def read_uint64(data, offset):
    return struct.unpack_from(">Q", data, offset)[0], offset + 8


def read_int32(data, offset):
    return struct.unpack_from(">i", data, offset)[0], offset + 4


def read_uint32(data, offset):
    return struct.unpack_from(">I", data, offset)[0], offset + 4


def read_int16(data, offset):
    return struct.unpack_from(">h", data, offset)[0], offset + 2


def read_uint16(data, offset):
    return struct.unpack_from(">H", data, offset)[0], offset + 2


def read_int8(data, offset):
    return struct.unpack_from(">b", data, offset)[0], offset + 1


def read_float(data, offset):
    return struct.unpack_from(">f", data, offset)[0], offset + 4


def read_char_array(data, offset, size):
    a = struct.unpack_from(">" + str(size) + "s", data, offset)[0]
    return a.decode("latin1"), offset + size


# Функция для разбора структуры D
def parse_struct_D(data, offset):
    result = {}
    result["D1"], offset = read_uint16(data, offset)
    result["D2"], offset = read_int32(data, offset)
    return result, offset


# Функция для разбора структуры C
def parse_struct_C(data, offset):
    result = {}
    result["C1"], offset = read_float(data, offset)
    result["C2"], offset = read_int16(data, offset)
    result["C3"], offset = read_uint32(data, offset)
    result["C4"] = []
    for _ in range(5):
        address, offset = read_uint32(data, offset)
        field, address = parse_struct_D(data, address)
        result["C4"].append(field)
    result["C5"], offset = read_int8(data, offset)
    result["C6"], offset = read_uint64(data, offset)
    return result, offset


# Функция для разбора структуры F
def parse_struct_F(data, offset):
    result = {}
    size, offset = read_uint16(data, offset)
    adr, offset = read_uint32(data, offset)
    f1 = []
    for i in range(size):
        val, adr = read_int8(data, adr)
        f1.append(val)
    result["F1"] = f1
    result["F2"], offset = read_uint8(data, offset)
    return result, offset


# Функция для разбора структуры E
def parse_struct_E(data, offset):
    result = {}
    result["E1"], offset = read_int16(data, offset)
    result["E2"], offset = read_int16(data, offset)
    result["E3"], offset = read_int16(data, offset)
    return result, offset


# Функция для разбора структуры B
def parse_struct_B(data, offset):
    result = {}
    result["B1"], offset = read_uint64(data, offset)
    result["B2"], offset = read_int8(data, offset)
    b3_offset, offset = read_uint16(data, offset)
    result["B3"], b3_offset = parse_struct_C(data, b3_offset)
    b4_offset, offset = read_uint16(data, offset)
    result["B4"], b4_offset = parse_struct_E(data, b4_offset)
    # result["b4"], offset = read_uint16(data, offset)
    result["B5"], offset = read_uint8(data, offset)
    result["B6"], offset = read_char_array(data, offset, 4)
    b7_offset, offset = read_uint32(data, offset)
    result["B7"], b7_offset = parse_struct_F(data, b7_offset)
    size, offset = read_uint16(data, offset)
    adr, offset = read_uint16(data, offset)
    result["B8"] = []
    for i in range(size):
        val, adr = read_uint16(data, adr)
        result["B8"].append(val)
    return result, offset


# Функция для разбора структуры G
def parse_struct_G(data, offset):
    result = {}
    result["G1"], offset = read_uint8(data, offset)
    result["G2"], offset = read_int32(data, offset)
    result["G3"], offset = read_int8(data, offset)
    result["G4"], offset = read_uint32(data, offset)
    return result, offset


# Главная функция для разбора данных
def main(data):
    offset = 0
    # Проверка сигнатуры
    signature = struct.unpack_from(">5B", data, offset)
    if signature != (0x50, 0x56, 0x59, 0x49, 0x4C):
        print("Invalid signature")
        return None

    offset += 5
    result = {}
    # Чтение данных структуры A
    result["A1"], offset = read_int64(data, offset)
    result["A2"], offset = parse_struct_B(data, offset)
    result["A3"], offset = read_float(data, offset)
    result["A4"], offset = read_int32(data, offset)
    result["A5"], offset = read_float(data, offset)
    result["A6"], offset = parse_struct_G(data, offset)
    a7 = []
    for i in range(2):
        val, offset = read_int8(data, offset)
        a7.append(val)
    result["A7"] = a7
    return result


# Пример использования
binary_data = (b'PVYIL\x81Fs\xf4\x88iW\x89g\x16\xe3!\xd1\xa0BB\xf2\x00]\x00\x84\xdboeka\x00'
 b'\x00\x00\x8e\x00\x03\x00\x95<}\rG\xa7c\xcc\xc8>T\x9a\x8c\x9c\xd0\xe6\x117'
 b'\xadW\x15q\xd4\x1d\xcaJ\\\xb7\x8fFC\xd8\xb2>\x85\xae\xdapwJ^tyj\x80\xbd'
 b'\xb3\xde\xbczQ\xfc\x8a\xc2\xc9>\x84\x9b_\xd8\x1bJD\xb9\xcc\x00\x00\x00?\x00'
 b"\x00\x00E\x00\x00\x00K\x00\x00\x00Q\x00\x00\x00W\x9e[w\x82\xf3'\x0fh\\"
 b'\xd3\xb6\x95\x8aB\x90\xa1\xf0\x0b\xc5\x00\x04\x00\x00\x00\x8aB\xd3\x08\xa7'
 b'\xc1\xed\xa0')

parsed_data = main(binary_data)
print(parsed_data)
