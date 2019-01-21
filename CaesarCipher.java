class CaesarCipher
{
    // Encrypts text using a shift od s
    public static StringBuffer encrypt(String text, int s)
    {
        StringBuffer result= new StringBuffer();

        for (int i=0; i<text.length(); i++)
        {
            if (Character.isUpperCase(text.charAt(i)))
            {
                char ch = (char)(((int)text.charAt(i) +
                                  s - 65) % 26 + 65);
                result.append(ch);
            }
            else
            {
                char ch = (char)(((int)text.charAt(i) +
                                  s - 97) % 26 + 97);
                result.append(ch);
            }
        }
        return result;
    }

    // Driver code
    public static void main(String[] args)
    {
        String text = "HELLO";
        int s = 4;
        System.out.println("Text  : " + text);
        System.out.println("Shift : " + s);
        String temp = encrypt(text,s).toString();
        System.out.println("Cipher: " + temp);
        System.out.println("Decrypted Text " + encrypt(temp,26-s));
    }
}
