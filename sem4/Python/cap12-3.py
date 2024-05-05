from struct import *


def parse(buffer, offset, type):
    size = calcsize(type)
    value = unpack_from(type, buffer, offset)[0]
    return value, offset + size


def parse_c(buffer, offset):
    c1, offset = parse(buffer, offset, '<d')
    c2, offset = parse(buffer, offset, '<f')
    return {'C1': c1, 'C2': c2}, offset


def parse_d(buffer, offset):
    d1, offset = parse(buffer, offset, '<Q')
    d2, offset = parse(buffer, offset, '<d')
    d3, offset = parse(buffer, offset, '<i')
    d4, offset = parse(buffer, offset, '<Q')
    d5 = []
    for _ in range(7):
        val, offset = parse(buffer, offset, '<b')
        d5.append(val)
    d6, offset = parse(buffer, offset, '<i')
    d7 = []
    for _ in range(6):
        val, offset = parse(buffer, offset, '<b')
        d7.append(val)
    return {'D1': d1, 'D2': d2, 'D3': d3, 'D4': d4, 'D5': d5, 'D6': d6,
            'D7': d7}, offset


def parse_b(buffer, offset):
    b1, offset = parse(buffer, offset, '<h')
    b2, offset = parse(buffer, offset, '>3s')
    b2 = b2.decode()
    b3, offset = parse(buffer, offset, '<i')

    b4 = []
    array_size, offset = parse(buffer, offset, '<H')
    adr_offset, offset = parse(buffer, offset, '<H')
    for _ in range(array_size):
        b4_offset, adr_offset = parse(buffer, adr_offset, '<H')
        val, _ = parse_c(buffer, b4_offset)
        b4.append(val)

    b5, offset = parse(buffer, offset, '<q')
    b6 = []
    for _ in range(2):
        val, offset = parse(buffer, offset, '<I')
        b6.append(val)
    return {'B1': b1, 'B2': b2, 'B3': b3, 'B4': b4, 'B5': b5, 'B6': b6}, offset


def parse_a(buffer, offset):
    a1, offset = parse_b(buffer, offset)
    a2, offset = parse(buffer, offset, '<d')
    a3, offset = parse(buffer, offset, '<H')
    a4, offset = parse(buffer, offset, '<h')
    a5_offset, offset = parse(buffer, offset, '<I')
    a5, _ = parse_d(buffer, a5_offset)
    a6, offset = parse(buffer, offset, '<Q')
    a7, offset = parse(buffer, offset, '<q')
    a8, offset = parse(buffer, offset, '<H')
    return {'A1': a1, 'A2': a2, 'A3': a3, 'A4': a4, 'A5': a5, 'A6': a6,
            'A7': a7, 'A8': a8}, offset


def main(data):
    result, _ = parse_a(data, 4)
    return result