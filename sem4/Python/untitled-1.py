import re

def parse_string(input_string):
    parsed_results = []
    sections = re.findall(r'<section>\s*set\s+(\w+)\s*:=\s*\[\s*(.*?)\s*]\s*</section>', input_string, re.DOTALL)
    
    for section in sections:
        var_name = section[0]
        elements = [elem.strip() for elem in section[1].split(';') if elem.strip()]
        parsed_results.append((var_name, elements))
    
    return parsed_results

# Пример использования:
input_string = r"\begin <section> set usesar_516 := [ zaesis_324 ; edat_614 ]. </section>; <section> set soer:= [ anso ; ais;raed ;xela_568 ]. </section>;\end"
result = parse_string(input_string)
print(result)












#def main(d):
    #d1 = bin(int(f['F1']))[2:].zfill(5)
    #d2 = bin(int(f['F2']))[2:].zfill(3)
    #d3 = bin(int(f['F3']))[2:].zfill(4)
    #d4 = bin(int(f['F4']))[2:].zfill(6)
    #print(hex(int(f4+f3+f2+f1, 2)))
 
#main({'D1': '1', 'D2': '178', 'D3': '98', 'D4': '2', 'D5': '1', 'D6': '7'})
#main({'D1': '1', 'D2': '390', 'D3': '105', 'D4': '7', 'D5': '1', 'D6': '5'})
#main({'D1': '1', 'D2': '355', 'D3': '508', 'D4': '7', 'D5': '1', 'D6': '0'})
#main({'D1': '0', 'D2': '425', 'D3': '358', 'D4': '9', 'D5': '0', 'D6': '4'})


#tree = {
    #1972: {1960: {"LEAN": 0, "NL": 1, "ATS": 2}, 2018: 3},
    #2020: {"ROFF": {"LEAN": 4, "NL": 5, "ATS": 6}, "JAVA": {1960: 7, 2018: 8}},
    #1992: 9,
#}


#def main(m: set, scope=tree):
    #match scope:
        #case dict(branch):
            #keys = branch.keys() & m
            #assert(len(keys) == 1)
            #return main(m, branch[next(iter(keys))])
        #case terminal_value:
            #return terminal_value

#s = ({1996,'NIX',1961,1966},
#{1996,'NIX',1961,2015},
#{1996,'NIX',1961,1979},
#{1996,'NIX',1981,2001},
#{1996,'NIX',1981,2009},
#{1996,'P4'},
#{1996,'MESON',1961,2001},
#{1996,'MESON',1961,2009},
#{1996,'MESON',1981},
#{2003,2001,1966},
#{2003,2001,2015},
#{2003,2001,1979},
#{2003,2009})
 
#def main(*r):
    #s1 = set(r)
    #mx = [len(i & s1) for i in s]
    #a = [i for i,j in enumerate(mx) if j == max(mx)]
    #mn = [len(s[i]) for i in a]
    #return a[mn.index(min(mn))]

#print('Ответ:', main(1961,2001,1996,'NIX',1966))
#print('Ответ:', main(1981,2009,1996,'MESON',1966))



#import math

#def main(M):
    ## Функция для вычисления H
    #def calculate_H(M):
        #H = set()
        #for mu in M:
            #if -76 <= mu <= 55:
                #H.add(math.ceil(mu / 3))
        #return H

    ## Функция для вычисления Omega
    #def calculate_Omega(M, H):
        #Omega = set()
        #for mu in M:
            #for eta in H:
                #if mu <= eta:
                    #Omega.add((mu, eta))
        #return Omega

    ## Функция для вычисления O
    #def calculate_O(H, Omega):
        #O = set()
        #for eta in H:
            #for mu, omega in Omega:
                #if eta > omega:
                    #O.add(eta % 3 + abs(omega))
        #return O

    ## Вычисление H, Omega, и O
    #H = calculate_H(M)
    #Omega = calculate_Omega(M, H)
    #O = calculate_O(H, Omega)

    ## Вычисление значения tau
    #tau = len(Omega)
    #for mu, omega in Omega:
        #tau += math.floor(mu / 9) + sum(eta % 2 for eta in O for omega in Omega)

    #return tau

## Примеры
#print(main({-96, 34, -62, -92, -88, -24, -51, -39, -71, 93}))  # Должно вывести 92517
#print(main({-61, -37, -23, 75, -50, 17, -11, -38, 27, -35}))  # Должно вывести 61834

