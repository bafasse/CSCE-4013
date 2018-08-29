alphabet = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'

def brute_force():
    message = "KRZ DUH BRX"

    for key in range(len(alphabet)):
        plain_text = ""
        if symbol in alphabet:
            num = alphabet.find(symbol)
            num -= key

            if num < 0:
                num += len(alphabet)

            translated += alphabet[num]
        else:
            plain_text += symbol

    print('Key #%s: %s' % (key, translated))
