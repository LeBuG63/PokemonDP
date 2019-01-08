import pokebase as pb
import sys
import requests
import re
from random import randint


def getAttackAttr(url):
    r = requests.get(url)
    
    json = r.json()

    data = {}

    data['power'] = json['power']
    data['accuracy'] = json['accuracy']

    return data 

def randomEv():
    sumev = 0
    ev = []

    for i in range(6):
        ev.append(0)

    while sumev < 512:
        index = randint(0, 5)
        data = randint(0, 200)

        if ev[index] + data > 255:
            sumev += 255 - data 
            ev[index] = 255
            continue

        if sumev + data > 512:
            data = 512 - sumev
            continue

        ev[index] = data
        sumev += data

    return ev

def printXML(name):
    pokemon = pb.pokemon(name.lower())

    os = ''

    os += '<Pokemon name="{}">\n'.format(pokemon.name.title())
    os += ' <id>{}</id>\n'.format(pokemon.id)
    os += ' <type>UNKNOWN</type>\n'
    os += ' <sprite>{}</sprite>\n'.format(pokemon.sprites.front_default)
    os += ' <baseStats>\n'
    for i in range(6):
        os += '  <bstat>{}</bstat>\n'.format(pokemon.stats[i].base_stat)

    ev = randomEv()

    os += "  <ev>\n"

    for e in ev:
        os += '<estat>{}</estat>\n'.format(e)

    os += '</ev>\n'

    os += """    <iv>
             <istat>32</istat>
             <istat>31</istat>
             <istat>31</istat>
             <istat>31</istat>
             <istat>31</istat>
             <istat>31</istat>
            </iv>
            </baseStats>
            <attacks>
            """

    nmoves = 0

    for moves in pokemon.moves:
        move = moves.move
        attr = getAttackAttr(move.url)
        if nmoves <= 3 and moves.version_group_details[0]['level_learned_at'] == 0:
            if attr['power']!= None:
                move.name = re.sub('[-]', ' ', move.name.title())
                os += '  <attack name="{}">\n'.format(move.name)
                os += '   <basePower>{}</basePower>\n'.format(attr['power'])
                os += '   <accuracy>{}</accuracy>\n'.format(attr['accuracy'])
                os += '   <isSpecial>false</isSpecial>\n'
                os += '   </attack>\n'
                nmoves += 1
        else:
          break 

    if nmoves == 0:
       return 

    os += ' </attacks>\n'
    os += '</Pokemon>\n'

    print(os)

counter = -1

if len(sys.argv) < 2:
    print('you must specify a file')
    sys.exit(1)

if len(sys.argv) == 3:
    counter = int(sys.argv[2])

f = open(sys.argv[1], "r")
print("""<?xml version="1.0" encoding="UTF-8" standalone="yes" ?>
        <list>""")

for line in f:
    if counter != -1:
        if counter == 0:
            break
        
        counter -= 1

    name = line.rstrip("\n\r")
    printXML(name)

print("</list>")

