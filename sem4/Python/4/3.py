#3.1
class Num:
    def __init__(self, value):
        self.value = value

    def accept(self, visitor):
        return visitor.visit_num(self)

class Add:
    def __init__(self, left, right):
        self.left = left
        self.right = right

    def accept(self, visitor):
        return visitor.visit_add(self)


class Mul:
    def __init__(self, left, right):
        self.left = left
        self.right = right

    def accept(self, visitor):
        return visitor.visit_mul(self)

#3.2
class PrintVisitor:
    def visit_num(self, node):
        return str(node.value)

    def visit_add(self, node):
        return f"({node.left.accept(self)} + {node.right.accept(self)})"

    def visit_mul(self, node):
        return f"({node.left.accept(self)} * {node.right.accept(self)})"

    def visit(self, node):
        return node.accept(self)

#3.3
class CalcVisitor:
    def visit_num(self, node):
        return node.value

    def visit_add(self, node):
        return node.left.accept(self) + node.right.accept(self)

    def visit_mul(self, node):
        return node.left.accept(self) * node.right.accept(self)

    def visit(self, node):
        return node.accept(self)

#3.4
class StackVisitor:
    def visit_num(self, node):
        return f"PUSH {node.value}"

    def visit_add(self, node):
        left = node.left.accept(self)
        right = node.right.accept(self)
        return f"{left}\n{right}\nADD"

    def visit_mul(self, node):
        left = node.left.accept(self)
        right = node.right.accept(self)
        return f"{left}\n{right}\nMUL"

    def visit(self, node):
        return node.accept(self)



ast = Add(Num(7), Mul(Num(3), Num(2)))

pv = PrintVisitor()
print(pv.visit(ast))  # Output: (7 + (3 * 2))

cv = CalcVisitor()
print(cv.visit(ast))  # Output: 13

#Код стековой машины
sv = StackVisitor()
print(sv.visit(ast))
# Output:
# PUSH 7
# PUSH 3
# PUSH 2
# MUL
# ADD

