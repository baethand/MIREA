from random import random, randint, choice
from functools import reduce


op = ['+', '-', '*', '/']


def gen_exp(max_deep, min_args, max_args, k=1):
    def gen_sub(deep = 0):
        sub = [str(randint(1, 9)) if random() * 3 * k < deep / max_deep else gen_sub(deep + 1) for _ in range(randint(min_args, max_args))]
        return f'({reduce(lambda a, b: choice(op).join((a, b)), sub)})'
    return gen_sub()

print("\n\n\n\n\n")
print(gen_exp(max_deep=4, min_args=2, max_args=2, k=0.3))
print("\n\n\n\n\n")
