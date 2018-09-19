import encrypt_decrypt
<<<<<<< HEAD
=======

>>>>>>> 32fa832169ba2596a068f5fa02311ed9736e51cd
alphabet = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'

def brute_force():
    user_message = input('Enter an encrypted word or sentance: ')
    user_message = user_message.upper()

    for key in range(len(alphabet)):
        plain_text = ''
        for letter in user_message:
            if letter in alphabet:
                num = alphabet.find(letter)
                num -= key

                if num < 0:
                    num += len(alphabet)

<<<<<<< HEAD
                plain_text = plain_text + alphabet[num]

=======
                plain_text += alphabet[num]
>>>>>>> 32fa832169ba2596a068f5fa02311ed9736e51cd
            else:
                plain_text += letter

            # print(letter)

            # if encrypt_decrypt.decrypt(key, user_message) == plain_text:
            #     right_key = key

        # if plain_text == encrypt_decrypt.decrypt(key, user_message):
        #             right_key = key

        print('Key #%s: %s' % (key, plain_text))
        # print(encrypt_decrypt.decrypt(key, user_message))
