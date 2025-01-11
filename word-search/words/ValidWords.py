READ_FILE = "words/wordlist.10000.txt"
WRITE_FILE = "words/valid_word_list.txt"

read = open(READ_FILE, 'r')
write = open(WRITE_FILE, 'w')

word = read.readline()
while (word):
    if len(word) > 4 and len(word) < 12:
        write.write(word)
    word = read.readline()