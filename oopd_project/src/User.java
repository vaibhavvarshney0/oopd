public abstract class User
{
    protected String name;
    protected int id;
    protected String email;
    protected int mobile;

    abstract int register(String senderId, String senderName, String postId, String[] input_token);
}

