package wordsearch;

public class Information
{
    public static String themes[] = new String[20];//stores themes
    public static String words[][] = new String[30][20];//stores words corresponding to the theme
    public static String creator[] = new String[20];//stores name of creator of the list
    public static int len[] = new int[20];//stores number of words in the list corresponding to the theme
    
    Information()//initialises pre-defined lists, lengths, creator names and themes
    {
        themes[0] = "Animals";
        for(int i = 0; i < 30; i++)
            words[i][0] = animals()[i];
        
        themes[1] = "Colours";
        for(int i = 0; i < 30; i++)
            words[i][1] = colours()[i];
        
        themes[2] = "Cities";
        for(int i = 0; i < 30; i++)
            words[i][2] = cities()[i];
        
        themes[3] = "Mythical Creatures";
        for(int i = 0; i < 30; i++)
            words[i][3] = creatures()[i];
        
        themes[4] = "Landforms";
        for(int i = 0; i < 30; i++)
            words[i][4] = landforms()[i];
        
        themes[5] = "Harry Potter Spells";
        for(int i = 0; i < 30; i++)
            words[i][5] = spells()[i];
            
        themes[6] = "Flowers";
        for(int i = 0; i < 30; i++)
            words[i][6] = flowers()[i];
            
        themes[7] = "Greek Mythology";
        for(int i = 0; i < 30; i++)
            words[i][7] = greek()[i];
            
        themes[8] = "Space";
        for(int i = 0; i < 30; i++)
            words[i][8] = space()[i];
            
        themes[9] = "Body";
        for(int i = 0; i < 30; i++)
            words[i][9] = body()[i];
          
        for(int i = 0; i < 10; i++)
        {
            creator[i] = "Default List";
            len[i] = 30;
        }
    }
    
    String[] animals()
    {
        String animals[] = {"ELEPHANT", "RHINOCEROS", "HIPPOPOTAMUS", "KANGAROO", "KOALA", "LION", "TIGER", "HYENA", "WHALE", "COBRA", "BUFFALO", "PIG", "HORSE", "GOAT", "SHEEP", "ALLIGATOR", "DEER", "MEERKAT", "LEMUR", "BADGER", "HEDGEHOG", "GORILLA", "BEAR", "DOG", "CAT", "MOUSE", "HAMSTER", "MOLE", "SEAL", "RACCOON"};
        return animals;
    }
    
    String[] colours()
    {
        String colours[] = {"BLACK", "BLUE", "BROWN", "GREEN", "RED", "GREY", "YELLOW", "ORANGE", "PINK", "WHITE", "TURQUOISE", "OCHRE", "BEIGE", "LAVENDER", "PURPLE", "VIOLET", "MAROON", "INDIGO", "PASTEL", "PEACH", "CERULEAN", "AMETHYST", "CINNAMON", "AMBER", "CRIMSON", "JADE", "SEPIA", "SAFFRON", "MUSTARD", "MAHOGANY"}; 
        return colours;
    }
    
    String[] cities()
    {
        String cities[] = {"MUMBAI", "DELHI", "CHENNAI", "KOLKATA", "SEOUL", "TOKYO", "SYDNEY", "KARACHI", "PHILADELPHIA", "SHANGHAI", "BEIJING", "PARIS", "RIO", "PERTH", "LONDON", "BARCELONA", "BELGRADE", "BANGKOK", "HONOLULU", "SEATTLE", "CHICAGO", "ROME", "LUXOR", "JAKARTA", "GLASGOW", "VANCOUVER", "ATHENS", "MOSCOW", "DUBAI", "BERLIN"}; 
        return cities;
    }
    
    String[] landforms()
    {
        String landforms[] = {"ARCHIPELAGO", "DELTA", "DESERT", "BASIN", "ISTHMUS", "ISLAND", "LAGOON", "ESCARPMENT", "PLATEAU", "BUTTE", "PENINSULA", "PLAIN", "OASIS", "SHORE", "SWAMP", "CLIFF", "CANYON", "HILL", "MOUNTAIN", "VALLEY", "RIDGE", "GLACIER", "MARSH", "FJORD", "HIGHLAND", "REEF", "WETLAND", "STRAIT", "CUESTA", "COVE"}; 
        return landforms;
    }
    
    String[] creatures()
    {
        String creatures[] = {"ELF", "GNOME", "WITCH", "WIZARD", "GIANT", "FAIRY", "PIXIE", "FRANKENSTEIN", "BIGFOOT", "UNICORN", "TROLL", "DRAGON", "CENTAUR", "SATYR", "GHOUL", "GARGOYLE", "CYCLOPS", "DEMON", "DRYAD", "YETI", "OGRE", "WEREWOLF", "KELPIE", "BANSHEE", "VAMPIRE", "LEPRACHAUN", "BASILISK", "BEHEMOTH", "ZOMBIE", "PHOENIX"}; 
        return creatures;
    }
    
    String[] spells()
    {
        String spells[] = {"OBLIVIATE", "LUMOS", "ALOHOMORA", "CRUCIO", "IMPERIO", "ACCIO", "AGUAMENTI", "CONFUNDO", "EXPELLIARMUS", "DIFFINDO", "RIDDIKULUS", "REPARO", "REPELLO", "SONOROUS", "SCOURGIFY", "LEVICORPUS", "LEGILIMENS", "OBSCURO", "NOX", "MUFFLIATO", "STUPEFY", "MUGGLETUM", "REDUCTO", "ERECTO", "ENGORGIO", "SILENCIO", "SECTUMSEMPRA", "MOBILICORPUS", "PORTUS", "IMMOBILUS"};
        return spells;
    }
    
    String[] flowers()
    {
        String spells[] = {"ROSE", "JASMINE", "SUNFLOWER", "DAISY", "PETUNIA", "LOTUS", "LILY", "BLUEBELL", "BUTTERCUP", "BOTTLEBRUSH", "CLOVER", "DAHLIA", "DAFFODIL", "PRIMROSE", "EDELWEISS", "GLADIOLUS", "FREESIA", "HOLLY", "LAVENDER", "HIBISCUS", "HYACINTH", "HEATHER", "IRIS", "LILAC", "MARIGOLD", "ORCHID", "VIOLETS", "ROSEMARY", "SAGE", "TULIPS"};
        return spells;
    }
    
    String[] space()
    {
        String spells[] = {"SUN", "MOON", "MERCURY", "VENUS", "EARTH", "MARS", "JUPITER", "SATURN", "URANUS", "NEPTUNE", "PLUTO", "COMET", "ASTEROID", "GALAXY", "STAR", "NEBULA", "SUPERNOVA", "ECLIPSE", "TITAN", "ANDROMEDA", "CONSTELLATION", "METEOR", "PLANET", "RADIATION", "SATELLITE", "UNIVERSE", "ORION", "BLACKHOLE", "AURORA", "COSMOS"};
        return spells;
    }
    
    String[] body()
    {
        String spells[] = {"STOMACH", "SPLEEN", "BRAIN", "NERVES", "INTESTINE", "SKULL", "PANCREAS", "KIDNEY", "VEIN", "SKIN", "EYE", "LUNGS", "LIVER", "EAR", "BLADDER", "MUSCLES", "SPINE", "NOSE", "URETER", "URETER", "TONGUE", "THYROID", "HEART", "RIBCAGE", "COLON", "LARYNX", "BRONCHUS", "OESOPHAGUS", "GALLBLADDER", "ARTERY"};
        return spells;
    }
    
    String[] greek()
    {
        String spells[] = {"ATHENA", "POSEIDON", "ZEUS", "HERMES", "APOLLO", "ARTEMIS", "POLYPHEMUS", "DIONYSUS", "HADES", "PERSEPHONE", "GAIA", "HESTIA", "DEMETER", "ARES", "APHRODITE", "CUPID", "HEPHAESTUS", "HERA", "PROMETHEUS", "ARACHNE", "ODYSSEUS", "ECHIDNA", "NEMESIS", "ATLAS", "KRONOS", "CALYPSO", "SPHINX", "PERSEPHONE", "PANDORA", "MEDUSA"};
        return spells;
    }
    
    String[] chooseWords(int num, String words[][], int len, int theme)//selects random words to insert in grid. Number of words is based on the level.
    {
        String words_copy[] = new String[len];
        
        for(int i = 0; i < len; i++)
            words_copy[i] = words[i][theme];
            
        String finalw[] = new String[num];
        
        for(int i = 0; i < num; i++)
        {
            int x = (int)((Math.random() * (len - 1)));//chooses a random index
            
            while(x >= len || x < 0)
                x = (int)((Math.random() * (len - 1)));
                
            finalw[i] = words_copy[x];
            
            for(int j = x + 1; j < len; j++)//to remove the used word from array so it may not be repeated
                words_copy[j - 1] = words_copy[j];                
            
            len--;
        }
        return finalw;
    }
}

