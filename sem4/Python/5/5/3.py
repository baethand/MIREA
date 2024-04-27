import unittest

class StackVisitor:
    def visit(self, node):
        method_name = 'visit_' + node.__class__.__name__
        visit_method = getattr(self, method_name, self.generic_visit)
        return visit_method(node)

    def generic_visit(self, node):
        raise RuntimeError(f'No visit_{node.__class__.__name__} method')

class Num:
    def __init__(self, value):
        self.value = value

    def accept(self, visitor):
        return visitor.visit(self)

class Add:
    def __init__(self, left, right):
        self.left = left
        self.right = right

    def accept(self, visitor):
        return visitor.visit(self)

class Mul:
    def __init__(self, left, right):
        self.left = left
        self.right = right

    def accept(self, visitor):
        return visitor.visit(self)

class StackVisitor(StackVisitor):
    def visit_Num(self, node):
        return f'PUSH {node.value}'

    def visit_Add(self, node):
        left_code = node.left.accept(self)
        right_code = node.right.accept(self)
        return f"{left_code}\n{right_code}\nADD"

    def visit_Mul(self, node):
        left_code = node.left.accept(self)
        right_code = node.right.accept(self)
        return f"{left_code}\n{right_code}\nMUL"

# Тесты
class TestStackVisitor(unittest.TestCase):
    def test_visit_Num(self):
        num_node = Num(5)
        sv = StackVisitor()
        self.assertEqual(sv.visit(num_node), "PUSH 5")

    def test_visit_Add(self):
        add_node = Add(Num(2), Num(3))
        sv = StackVisitor()
        self.assertEqual(sv.visit(add_node), "PUSH 2\nPUSH 3\nADD")

    def test_visit_Mul(self):
        mul_node = Mul(Num(4), Num(5))
        sv = StackVisitor()
        self.assertEqual(sv.visit(mul_node), "PUSH 4\nPUSH 5\nMUL")

    def test_nested_operations(self):
        ast = Add(Num(7), Mul(Num(3), Num(2)))
        sv = StackVisitor()
        self.assertEqual(sv.visit(ast), "PUSH 7\nPUSH 3\nPUSH 2\nMUL\nADD")

if __name__ == '__main__':
    unittest.main()