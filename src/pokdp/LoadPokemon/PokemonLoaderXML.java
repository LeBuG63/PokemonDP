package pokdp.LoadPokemon;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
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
<<<<<<< HEAD
    private final DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

    public HashMap<String, Pokemon> load(String path) {
=======
    public static HashMap<String, Pokemon> load(String path) {
        final DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
>>>>>>> origin/combat_screen
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

                    //     private int[] arrBaseStats , arrEV , arrIV, currentStats;

                    final NodeList baseStats = pokemon.getElementsByTagName(("bstat"));
                    final NodeList ev = pokemon.getElementsByTagName(("estat"));
                    final NodeList iv = pokemon.getElementsByTagName(("istat"));


                    int[] arrBaseStats = getArrayIntWithElements(baseStats);
                    int[] arrEV = getArrayIntWithElements(ev);
                    int[] arrIV = getArrayIntWithElements(iv);

                    pokemonHashmap.put(name, new Pokemon(name, arrBaseStats, arrEV, arrIV, 0, EType.valueOf(type.getTextContent())));
                }
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }

        return pokemonHashmap;
    }

<<<<<<< HEAD
    private int[] getArrayIntWithElements(NodeList elements) {
=======
    private static int[] getArrayIntWithElements(NodeList elements) {
>>>>>>> origin/combat_screen
        final int length = elements.getLength();

        int[] arr = new int[length];

        for(int i = 0; i < length; ++i) {
            final Element element = (Element)elements.item(i);
            arr[i] = Integer.parseInt(element.getTextContent());
        }

        return arr;
    }
}
