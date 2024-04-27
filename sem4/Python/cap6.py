import math

def calculate_tau(M):
    omega = set()
    for mu in M:
        for eta in calculate_H(M):
            if mu <= eta:
                omega.add(mu * eta)
    tau = len(omega)
    for omega_val in omega:
        for o in range(3):
            tau += (omega_val // 9 + o) % 2
    return tau

def calculate_H(M):
    H = set()
    for mu in M:
        if -76 <= mu <= 55:
            H.add(math.ceil(mu / 3))
    return H

def calculate_O(M):
    omega = set()
    H = calculate_H(M)
    for mu in M:
        for eta in H:
            if mu <= eta:
                omega.add(mu * eta)
    O = set()
    for eta in H:
        for omega_val in omega:
            if eta > omega_val:
                O.add(eta % 3 + abs(omega_val))
    return O

def main(M):
    tau = calculate_tau(M)
    O = calculate_O(M)
    print("tau =", tau)
    print("O =", O)

# Пример вызова функции
main({-96, 34, -62, -92, -88, -24, -51, -39, -71, 93})
