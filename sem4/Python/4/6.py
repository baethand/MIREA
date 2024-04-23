OP_NAMES = {0: 'push', 1: 'op', 2: 'call', 3: 'is', 4: 'to', 5: 'exit'}

def disasm(bytecode):
    pc = 0
    entry = bytecode[0]
    print("entry:")
    while pc < len(bytecode):
        op = bytecode[pc]
        arg = bytecode[pc + 1]
        pc += 2
        op_name = OP_NAMES.get(op, 'unknown')  # Using get method with a default value
        print(f"  {op_name} {arg}")
        if op == 5:
            break

# Пример использования
disasm([0, 16, 16, 1, 121, 5])

class VM:
    def __init__(self, code):
        self.stack = []
        self.code = code
        self.pc = code[0]

    def run(self):
        while True:
            op = self.code[self.pc]
            arg = self.code[self.pc + 1]
            self.pc += 2

            if op == 0:  # push
                self.stack.append(arg)
            elif op == 1:  # op
                operator = self.stack.pop()
                operand = self.stack.pop()
                if operator == '+':
                    result = operand + operator
                elif operator == '-':
                    result = operand - operator
                elif operator == '*':
                    result = operand * operator
                elif operator == '/':
                    result = operand // operator
                elif operator == '%':
                    result = operand % operator
                elif operator == '&':
                    result = operand & operator
                elif operator == '|':
                    result = operand | operator
                elif operator == '^':
                    result = operand ^ operator
                elif operator == '<':
                    result = operand < operator
                elif operator == '>':
                    result = operand > operator
                elif operator == '=':
                    result = operand == operator
                elif operator == '<<':
                    result = operand << operator
                elif operator == '>>':
                    result = operand >> operator
                print(result)
            elif op == 5:  # exit
                break

# Пример использования
vm = VM([0, 16, 16, 1, 121, 5])
vm.run()
