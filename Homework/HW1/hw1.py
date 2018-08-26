key = input("Input a number between [0,25]: ")
while int(key) < 0 or int(key) > 25:
    print("You have entered a number outside the range. Try again")
    offset = input("Input a number between [0,25]: ")

plaintext = input("Input a word to enter at the beginning of the alphabet: ")
print("key = " + key)
print("plaintext = " + plaintext)

