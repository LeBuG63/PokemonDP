package pokdp.LoadPokemon;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import pokdp.Attack.Attack;
import pokdp.Entity.Pokemon.Pokemon;
import pokdp.Type.EType;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class PokemonLoaderXML implements IPokemonLoader {
    public static HashMap<String, Pokemon> load(String path) {
        final DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        HashMap<String, Pokemon> pokemonHashmap = new HashMap<>();

        try {
            final DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            final Document document = documentBuilder.parse(path);

            final Element root = document.getDocumentElement();

            final NodeList  rootNodes = root.getChildNodes();
            final int       nRootNodes = rootNodes.getLength();

            for(int i = 0; i < nRootNodes; ++i) {
                if(rootNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    final Element pokemon = (Element) rootNodes.item(i);

                    final String name = pokemon.getAttribute("name");
                    final Element type = (Element)pokemon.getElementsByTagName("type").item(0);
                    final Element sprite = (Element)pokemon.getElementsByTagName("sprite").item(0);
                    //     private int[] arrBaseStats , arrEV , arrIV, currentStats;

                    final NodeList baseStats = pokemon.getElementsByTagName(("bstat"));
                    final NodeList ev = pokemon.getElementsByTagName("estat");
                    final NodeList iv = pokemon.getElementsByTagName("istat");
                    final NodeList attacks = pokemon.getElementsByTagName("attack");

                    int[] arrBaseStats = getArrayIntWithElements(baseStats);
                    int[] arrEV = getArrayIntWithElements(ev);
                    int[] arrIV = getArrayIntWithElements(iv);

                    List<Attack> attackList = getAttacksWithElements(attacks);

                    String sSprite = String.valueOf(sprite.getTextContent());

                    Pokemon pok = new Pokemon(name, sSprite, arrBaseStats, arrEV, arrIV, 5, EType.valueOf(type.getTextContent()));
                    pok.addAllAttacks(attackList);

                    pokemonHashmap.put(name, pok);
                }
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }

        return pokemonHashmap;
    }

    private static List<Attack> getAttacksWithElements(NodeList elements) {
        List<Attack> attackList = new ArrayList<>();

        for(int i = 0; i < elements.getLength(); ++i) {
            if(elements.item(i).getNodeType() == Node.ELEMENT_NODE) {
                final Element attack = (Element)elements.item(i);

                final String name = attack.getAttribute("name");
                final Element elementBasePower = (Element)attack.getElementsByTagName("basePower").item(0);
                final Element elementAccuracy = (Element)attack.getElementsByTagName("basePower").item(0);
                final Element elementIsSpecial = (Element)attack.getElementsByTagName("isSpecial").item(0);

                final int basePower = Integer.parseInt(elementBasePower.getTextContent());
                final boolean isSpecial = Boolean.parseBoolean(elementIsSpecial.getTextContent());
                final int accuracy = Integer.parseInt(elementAccuracy.getTextContent());

                attackList.add(new Attack(name, basePower, isSpecial, accuracy));
            }
        }

        return attackList;
    }

    private static int[] getArrayIntWithElements(NodeList elements) {

        final int length = elements.getLength();

        int[] arr = new int[length];

        for(int i = 0; i < length; ++i) {
            final Element element = (Element)elements.item(i);
            arr[i] = Integer.parseInt(element.getTextContent());
        }

        return arr;
    }
}
