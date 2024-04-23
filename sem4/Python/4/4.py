class Tag:
    def __init__(self, name):
        self.name = name
        self.children = []

    def __enter__(self):
        return self

    def __exit__(self, exc_type, exc_value, traceback):
        pass

    def add_child(self, child):
        self.children.append(child)
        return child

    def get_code(self, indent=0):
        code = '  ' * indent + f'<{self.name}>\n'
        for child in self.children:
            if isinstance(child, Tag):  # Check if child is a Tag object
                code += child.get_code(indent + 1)
            else:
                code += '  ' * (indent + 1) + "<p>\"" + child + '"<\p>\n'  # If not, assume it's a string
        code += '  ' * indent + f'</{self.name}>\n'
        return code


class HTML:
    def __init__(self):
        self.body_tag = Tag('body')

    def body(self):
        return self.body_tag

    def get_code(self):
        return self.body_tag.get_code()


html = HTML()
with html.body() as body:
    with body.add_child(Tag('div')) as div1:
        with div1.add_child(Tag('div')) as div2:
            div2.add_child(Tag('p').add_child('Первая строка.'))
            div2.add_child(Tag('p').add_child('Вторая строка.'))
        with body.add_child(Tag('div')) as div3:
            div3.add_child(Tag('p').add_child('Третья строка.'))

print(html.get_code())
