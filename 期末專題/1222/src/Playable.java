public interface Playable {
    void play(User user) throws Exception;
    void pause();
    void resume();
    void seek(int positionSeconds);
}