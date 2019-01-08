package pokdp.Serialization;

public abstract class IObjectSerializationManager {
    public abstract Object readObjectFromFile(String path);
    public abstract void writeObjectFromFile(String path, Object item);
}
