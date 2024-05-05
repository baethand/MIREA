from struct import *


def parse(buffer, offset, type, order='<'):
    pattern = {
        'float': 'f',
        'double': 'd',
        'char': 'c',
        'int8': 'b',
        'uint8': 'B',
        'int16': 'h',
        'uint16': 'H',
        'int32': 'i',
        'uint32': 'I',
        'int64': 'q',
        'uint64': 'Q'
    }[type]
    size = calcsize(order + pattern)
    value = unpack_from(order + pattern, buffer, offset)[0]
    return value, offset + size

def parse_g(buffer, offset):
    g1, offset = parse(buffer, offset, 'uint8')
    g2, offset = parse(buffer, offset, 'int32')
    g3, offset = parse(buffer, offset, 'int8')
    g4, offset = parse(buffer, offset, 'uint32')
    return {'G1': g1, 'G2': g2, 'G3': g3, 'G4': g4}, offset

def parse_f(buffer, offset):
    #----------------------
    f1 = []
    array_size, offset = parse(buffer, offset, 'uint16')
    adr_offset, offset = parse(buffer, offset, 'uint32')
    for _ in range(array_size):
        val, offset = parse(buffer, offset, 'int8')
        f1.append(val)
    #----------------------
    f2, offset = parse(buffer, offset, 'uint8')
    return {'F1': f1, 'F2': f2}, offset

def parse_e(buffer, offset):
    e1, offset = parse(buffer, offset, 'int16')
    e2, offset = parse(buffer, offset, 'int16')
    e3, offset = parse(buffer, offset, 'int16')
    return {'E1': e1, 'E2': e2, 'E3': e3}, offset


def parse_c(buffer, offset):
    c1, offset = parse(buffer, offset, 'float')
    
    c2, offset = parse(buffer, offset, 'int16')
    
    c3, offset = parse(buffer, offset, 'uint32')
    
    #----------------------
    c4 = []
    for _ in range(5):
        val_offset, offset = parse(buffer, offset, 'uint32')
        val, _ = parse_d(buffer, val_offset)
        c4.append(val)
    #----------------------
    c5, offset = parse(buffer, offset, 'int8')
    c6, offset = parse(buffer, offset, 'uint64')
    return {'C1': c1, 'C2': c2, 'C3': c3, 'C4': c4, 'C5': c5, 'C6': c6}, offset


def parse_d(buffer, offset):
    d1, offset = parse(buffer, offset, 'uint16')
    d2, offset = parse(buffer, offset, 'int32')
    return {'D1': d1, 'D2': d2}, offset


def parse_b(buffer, offset):
    b1, offset = parse(buffer, offset, 'uint64')
    b2, offset = parse(buffer, offset, 'int8')
    b3_offset, offset = parse(buffer, offset, 'uint16')
    b3, _ = parse_c(buffer, b3_offset)
    b4_offset, offset = parse(buffer, offset, 'uint16')
    b4, _ = parse_e(buffer, b4_offset)
    b5, offset = parse(buffer, offset, 'uint8')
    b6 = buffer[offset:offset+4].decode('ascii')
    offset += 4
    b7_offset, offset = parse(buffer, offset, 'uint32')
    b7, _ = parse_f(buffer, b7_offset)

    #---------------------------------------------------------------------
    b8 = []
    array_size, offset = parse(buffer, offset, 'uint16')
    adr_offset, offset = parse(buffer, offset, 'uint16')
    for _ in range(array_size):
        b8_offset, adr_offset = parse(buffer, adr_offset, 'uint16')
        val, _ = parse(buffer, b8_offset, 'uint16')
        b8.append(val)

    return {'B1': b1, 'B2': b2, 'B3': b3, 'B4': b4, 'B5': b5,
            'B6': b6, 'B7': b7, 'B8': b8}, offset


def parse_a(buffer, offset):
    offset = 7
    a1, offset = parse(buffer, offset, 'int64')
    a2, offset = parse_b(buffer, offset)
    a3, offset = parse(buffer, offset, 'float')
    a4, offset = parse(buffer, offset, 'int32')
    a5, offset = parse(buffer, offset, 'float')
    a6, offset = parse_g(buffer, offset)
    a7 = []
    offset+=2
    for _ in range(2):
        val, offset = parse(buffer, offset, 'int8')
        a7.append(val)
    return {'A1': a1, 'A2': a2, 'A3': a3, 'A4': a4, 'A5': a5, 'A6': a6,
            'A7': a7}, offset


def main(data):
    result, _ = parse_a(data, 0)
    return result

print(main(b'PVYIL\xb1\xdf\xf4\r\x85\xda\xb3\x17\xcb\xee\xf7\xd2H\x1f\x05\xca\x00\x00]'
 b'\x00\x840juco\x00\x00\x00\x8c\x00\x02\x00\x93\xbd\xd0\xf9}*\xfb\xa2\x05\xbf'
 b'\x05\xde,0\x9fq]i\xa1+nh\x9bzS\x19\xcd7\xd6\x06ou\xdf\xf6z9\xef,'
 b'\xca\x93\xa3\xaf\xccb\x9e\xb2\x19\x06\x95m\xf5\xaen\xcdA?\x15\xb4\x1a\x145j'
 b'\xed\xa2Y\x00\x00\x00?\x00\x00\x00E\x00\x00\x00K\x00\x00\x00Q\x00\x00\x00Wp'
 b'v\x1cI)\xa6\xa1f6g\x8b\xf5@\xe3\x812I\x00\x02\x00\x00\x00\x8a\xfeR'
 b'\xc7\xf0\xe7'))