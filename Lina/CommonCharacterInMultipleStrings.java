/*Write a program that gives count of common characters presented in an array of strings..(or array of character arrays) 

For eg.. for the following input strings.. 

aghkafgklt 
dfghako 
qwemnaarkf 

The output should be 3. because the characters a, f and k are present in all 3 strings. 

Note: The input strings contains only lower case alphabets*/


public static void findCommonCharacters(List<String> strings)
    {
        int[] chars = new int[26];

        for ( String str : strings )
        {
            int[] stringcharacters = new int[26];
            
            for ( int i = 0; i < str.length(); i++ )
            {
                char c = str.charAt(i);
                stringcharacters[c - 'a']++;
            }

            for ( int i = 0; i < 26; i++ )
            {
                if ( stringcharacters[i] > 0 )
                    chars[i]++;
            }
        }

        for ( int i = 0; i < 26; i++ )
        {
            if ( chars[i] == strings.size() )
                System.out.println((char) ('a' + i));
        }
    }