public static String FindAndReplace(String GivenString, String Pattern, String ReplaceString)
        {
            int j = 0;
            int tempi;

            for (int i = 0; i < GivenString.Length; i++)
            {
                tempi = i;
                j = 0;
                while (i<GivenString.Length && j< Pattern.Length && GivenString[i] == Pattern[j])//trying to match the pattern
                {
                    i++;
                    j++;
                }
                if (j == Pattern.Length)//if we find the given pattern
                {
                    GivenString = GivenString.Substring(0, tempi) + ReplaceString + GivenString.Substring(i, GivenString.Length - i);
                    i = tempi + ReplaceString.Length-1;
                    continue; 
                }
                //if we didn't find it, set i back
                i = tempi;
            }
            return GivenString;
        }