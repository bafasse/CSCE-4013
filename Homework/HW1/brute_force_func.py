import encrypt_decrypt
alphabet = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'

def brute_force():
    user_message = input('Enter an encrypted word or sentance: ')
    user_message = user_message.upper()

    for key in range(len(alphabet)):
        plain_text = ''
        for symbol in user_message:
            if symbol in alphabet:
                num = alphabet.find(symbol)
                num -= key

                if num < 0:
                    num += len(alphabet)

                plain_text = plain_text + alphabet[num]

            else:
                plain_text = plain_text + symbol

        # if plain_text == encrypt_decrypt.decrypt(key, user_message):
        #             right_key = key

        print('Key #%s: %s' % (key, plain_text))
