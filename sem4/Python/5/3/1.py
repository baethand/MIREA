import random
from collections import defaultdict
import inspect
import ast

def buggy_function(x):
    return x / 0
class Mutator(ast.NodeTransformer):
    def visit_Constant(self, node):
        if isinstance(node.value, (int, float)):
            node.value = random.uniform(-1000, 1000)
        return node


def mutate_code(src):
    tree = ast.parse(src)
    Mutator().visit(tree)
    return ast.unparse(tree)


def make_mutants(func, size):
    mutant = src = ast.unparse(ast.parse(inspect.getsource(func)))
    mutants = [src]
    while len(mutants) < size + 1:
        while mutant in mutants:
            mutant = mutate_code(src)
        mutants.append(mutant)
    return mutants[1:]


def mut_test(func, test, size=20):
    survived = []
    mutants = make_mutants(func, size)
    for mutant in mutants:
        try:
            exec(mutant, globals())
            test()
            survived.append(mutant)
        except:
            pass
    return survived


# Тестирование функции
def test_buggy_function():
    # Тестирование деления на ноль
    assert buggy_function(10) == float('inf')


# Пример использования
survived_mutants = mut_test(buggy_function, test_buggy_function, size=100)
print("Survived mutants:", survived_mutants)