class SVG:
    def __init__(self):
        self.elements = []

    def line(self, x1, y1, x2, y2, color='black'):
        self.elements.append(f'<line x1="{x1}" y1="{y1}" x2="{x2}" y2="{y2}" stroke="{color}" />')

    def circle(self, cx, cy, r, color='black'):
        self.elements.append(f'<circle cx="{cx}" cy="{cy}" r="{r}" fill="{color}" />')

    def save(self, filename, width, height):
        with open(filename, 'w') as f:
            f.write(f'<svg version="1.1" width="{width:.6f}" height="{height:.6f}" xmlns="http://www.w3.org/2000/svg">\n')
            for element in self.elements:
                f.write(element + '\n')
            f.write('</svg>')

svg = SVG()

svg.line(10, 10, 60, 10, color='black')
svg.line(60, 10, 60, 60, color='black')
svg.line(60, 60, 10, 60, color='black')
svg.line(10, 60, 10, 10, color='black')

svg.circle(10, 10, r=5, color='red')
svg.circle(60, 10, r=5, color='red')
svg.circle(60, 60, r=5, color='red')
svg.circle(10, 60, r=5, color='red')

svg.save('pic5-1.svg', 100, 100)
