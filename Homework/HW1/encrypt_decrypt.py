alphabet = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'

def encrypt(key, plain_text):
    cipher = []
    for i in plain_text:
        if i.strip() and i in alphabet:
            cipher.append(alphabet[(alphabet.index(i) + int(key)) % 26])
        else:
            cipher.append(i)
    cipher_text = ''.join(cipher)
    return cipher_text


def decrypt(key, cipher_text):
    plain_text = []
    for i in cipher_text:
        if i.strip() and i in plain_text:
            plain_text.append(alphabet[(alphabet.index(i) - int(key)) % 26])
        else:
            plain_text.append(i)
    text = ''.join(plain_text)
    return text

