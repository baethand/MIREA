from controlled_module import *

func1()
print(var1)

# func2()  # NameError: name 'func2' is not defined
# print(var2)  # NameError: name 'var2' is not defined

"""
Чтобы сделать импорт с помощью * контролируемым со стороны модуля,
 вы можете использовать специальную переменную __all__ в вашем модуле. 
 Переменная __all__ должна быть списком, содержащим имена тех объектов,
  которые должны быть импортированы при использовании from module import *.
"""