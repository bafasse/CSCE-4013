def encrypt(k, p):
    alphabet = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'
    cipher_alphabet = []
    for i in p:
        if i.strip() and i in alphabet:
            cipher_alphabet.append(alphabet[(alphabet.index(i) + int(k)) % 26])
        else:
            cipher_alphabet.append(i)
    cipher_text = ''.join(cipher_alphabet)
    return cipher_text

    

key = input("Input a number between [1,25]: ")
while int(key) < 1 or int(key) > 25:
    print("You have entered a number outside the range. Try again")
    offset = input("Input a number between [0,25]: ")

plaintext = input("Input a word to enter at the beginning of the alphabet: ")
plaintext = plaintext.upper()
print(encrypt(key, plaintext))