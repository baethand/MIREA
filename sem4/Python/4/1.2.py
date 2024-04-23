class MyClass:
    def method1(self):
        print("Method 1 called")

    def method2(self):
        print("Method 2 called")

obj = MyClass()

# Вызов метода по имени
method_name = "method1"
getattr(obj, method_name)()
