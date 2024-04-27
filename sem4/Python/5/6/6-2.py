import matplotlib.pyplot as plt
import networkx as nx

# Создаем ключ
def create_key(color, room):
    return f"{color}_key='{room}'"

# Добавляем переход между состояниями
def add_transition(graph, source_state, target_state, action):
    graph.add_edge(source_state, target_state, action=action)

# Начальное состояние игры
START_STATE = dict(
    player='alice',
    alice_room='west room',
    bob_room='east room',
    red_key='east room',
    blue_key='west room',
    green_key='east room'
)

# Создаем граф состояний игры
G = nx.DiGraph()
start_state_idx = 0
G.add_node(start_state_idx, **START_STATE)

# Определяем возможные действия
possible_moves = [
    ('alice', 'west room', 'alice_room'),
    ('alice', 'east room', 'alice_room'),
    ('bob', 'west room', 'bob_room'),
    ('bob', 'east room', 'bob_room')
]

# Добавляем рёбра для перемещения персонажей
for player, destination, room in possible_moves:
    action_description = f"Move {player} to {destination}"
    next_state = START_STATE.copy()
    next_state['player'] = player
    next_state[room] = destination
    G.add_node(len(G), **next_state)
    add_transition(G, start_state_idx, len(G) - 1, action_description)

# Взятие ключа
colors = ['red', 'blue', 'green']
for color in colors:
    key_color = color + '_key'
    room_color = color + '_room'
    if key_color in START_STATE and room_color in START_STATE and START_STATE[key_color] != START_STATE[room_color]:
        action_description = f"Take {color} key"
        next_state = START_STATE.copy()
        next_state[create_key(color, START_STATE[color])] = 'held'
        G.add_node(len(G), **next_state)
        add_transition(G, start_state_idx, len(G) - 1, action_description)


# Открытие двери
for color in colors:
    action_description = f"Open {color} door"
    next_state = START_STATE.copy()
    next_state[color + "_door"] = 'open'
    G.add_node(len(G), **next_state)
    add_transition(G, start_state_idx, len(G) - 1, action_description)

# Передача ключа другому персонажу
for color in colors:
    key_color = color + '_key'
    if key_color in START_STATE:
        for player in ['alice', 'bob']:
            action_description = f"Give {color} key to {player}"
            next_state = START_STATE.copy()
            next_state[key_color] = START_STATE[player + "_room"]
            G.add_node(len(G), **next_state)
            add_transition(G, start_state_idx, len(G) - 1, action_description)

# Добавляем рычаг в комнату
START_STATE['lever_room'] = 'room3'

# Добавляем переход по рычагу
action_description = "Pull lever"
next_state = START_STATE.copy()
next_state['lever'] = 'pulled'
G.add_node(len(G), **next_state)
add_transition(G, start_state_idx, len(G) - 1, action_description)

# Делаем двусторонний переход
add_transition(G, len(G) - 1, start_state_idx, "Pull lever")

def find_dead_ends(graph):
    dead_ends = []
    for node in graph.nodes():
        successors = list(graph.successors(node))
        if len(successors) == 0:
            dead_ends.append(node)
    return dead_ends

# Поиск тупиковых узлов
dead_ends = find_dead_ends(G)
print("Dead ends:", dead_ends)

# Рисуем граф состояний игры
plt.figure(figsize=(12, 10))

node_colors = ['skyblue' if node != start_state_idx else 'blue' for node in G.nodes()]
for dead_end in dead_ends:
    node_colors[dead_end] = 'green'

pos = nx.spring_layout(G, seed=42)
nx.draw(G, pos, with_labels=True, node_size=3000, node_color=node_colors, font_size=10, font_weight='bold')

plt.title("State Space Graph for TTTM")
plt.show()