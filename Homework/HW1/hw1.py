import encrypt_decrypt
import brute_force_func

key = input("Enter a number between [1,25]: ")
while int(key) < 1 or int(key) > 25:
    print("You have entered a number outside the range. Try again")
    offset = input("Input a number between [0,25]: ")

text = input("Enter a word to have it encrypted: ")
text = text.upper()

print("----- Encryption/Decryption -----")
print(encrypt_decrypt.encrypt(key, text))
print(encrypt_decrypt.decrypt(key, text))

print("----- Brute Force ---------------")
brute_force_func.brute_force()