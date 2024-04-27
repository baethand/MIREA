import random
import ast
import inspect
from collections import defaultdict
from operator import add, sub, mul, truediv


class Mutator(ast.NodeTransformer):
    def visit_Constant(self, node):
        if isinstance(node.value, (int, float)):
            node.value = random.uniform(-1000, 1000)
        return node

    def visit_BinOp(self, node):
        # Вставляем случайную бинарную операцию
        operators = [add, sub, mul, truediv]
        random_op = random.choice(operators)
        node.op = ast.Name(id=random_op.__name__, ctx=ast.Load())
        return node


def mutate_code(src):
    tree = ast.parse(src)
    tree = Mutator().visit(tree)  # Применяем мутации к дереву
    return ast.unparse(tree)


def make_mutants(func, size):
    src = ast.unparse(ast.parse(inspect.getsource(func)))
    mutants = [src]
    for _ in range(size):
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
        except AssertionError:
            pass
        except SyntaxError:
            pass  # Пропускаем мутанты с некорректным синтаксисом
    return survived


# Тестируемая функция (сортировка)
def sort_list(lst):
    return sorted(lst)


# Тестирование функции
def test_sort_list():
    # Тестирование на пустом списке
    assert sort_list([]) == []
    
    # Тестирование на отсортированном списке
    assert sort_list([1, 2, 3, 4, 5]) == [1, 2, 3, 4, 5]
    
    # Тестирование на обратно отсортированном списке
    assert sort_list([5, 4, 3, 2, 1]) == [1, 2, 3, 4, 5]
    
    # Тестирование на случайном списке
    lst = [random.randint(0, 100) for _ in range(10)]
    assert sort_list(lst) == sorted(lst)


# Пример использования
survived_mutants = mut_test(sort_list, test_sort_list, size=100)
print("Survived mutants:", survived_mutants)