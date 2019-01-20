package pokdp.Stream.Serialization;

public interface IObjectSerializationManager {
    Object readObjectFromFile(String path);
        void writeObjectFromFile(String path, Object item);
}
