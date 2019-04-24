from blessed import Terminal

term = Terminal()

print("press 'q' to quit.")
with term.cbreak():
    val = ''
    while val.lower() != 'q':
        val = term.inkey(timeout=5)
        if not val:
            # timeout
            print("It sure is quiet in here ...")
        elif val.is_sequence:
            print("got sequence: {0}.".format((str(val), val.name, val.code)))
        elif val:
            print("got {0}.".format(val))
    print('bye!')
