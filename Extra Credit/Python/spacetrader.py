from view.activitycontroller import ActivityController
from view.main import MainActivity

from blessed import Terminal


def main():
    term = Terminal()
    ac = ActivityController(term)
    with term.fullscreen():
        with term.hidden_cursor():
            ac.start_activity(MainActivity)
            while len(ac.activity_stack) > 0:
                ac.current_activity.update()


if __name__ == "__main__":
    main()
