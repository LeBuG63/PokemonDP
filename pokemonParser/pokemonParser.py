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

    print('<Pokemon name="%s">' % pokemon.name.title())
    print(' <type>UNKNOWN</type>')
    print(' <sprite>%s</sprite>' % pokemon.sprites.front_default)
    print(' <baseStats>')
    for i in range(6):
        print('  <bstat>%s</bstat>' % pokemon.stats[i].base_stat)

    ev = randomEv()

    print("  <ev>")

    for e in ev:
        print('<estat>%d</estat>' % e)

    print("</ev>")

    print("""    <iv>
             <istat>32</istat>
             <istat>31</istat>
             <istat>31</istat>
             <istat>31</istat>
             <istat>31</istat>
             <istat>31</istat>
            </iv>""")
    print(' </baseStats>')
    print(' <attacks>')

    for moves in pokemon.moves:
        move = moves.move
        attr = getAttackAttr(move.url)
        if moves.version_group_details[0]['level_learned_at'] == 0:
            if attr['power']!= None:
                move.name = re.sub('[-]', ' ', move.name.title())
                print('  <attack name="%s">' % move.name)
                print('   <basePower>%d</basePower>' % attr['power'])
                print('   <accuracy>%d</accuracy>' % attr['accuracy'])
                print('   <isSpecial>false</isSpecial>')
                print('  </attack>')
        else:
            break

    print(' </attacks>')
    print('</Pokemon>')

if len(sys.argv) < 2:
    print('you must specify a file')
    sys.exit(1)

f = open(sys.argv[1], "r")
print("""<?xml version="1.0" encoding="UTF-8" standalone="yes" ?>
        <list>""")
for line in f:
    name = line.rstrip("\n\r")
    printXML(name)

print("</list>")

