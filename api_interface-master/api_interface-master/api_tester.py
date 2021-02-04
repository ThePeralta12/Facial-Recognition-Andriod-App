import sys, cmd, os
import requests
import json, pprint


class Shell(cmd.Cmd):
    intro = '\n Type h for help\n'
    prompt = '$ '
    #file = None

    def do_ca(self, arg):
        arg = arg.split(' ')
        if(len(arg) != 4):
            print('[!] cc <email> <password> <first-name> <last-name>')
            return

        payload = {
            'email': arg[0],
            'password': arg[1],
            'fname': arg[2],
            'lname': arg[3]
        }
        data=json.dumps(payload)
        r = requests.post('http://localhost:8000/api/create_account', data)
        pprint.pprint(r.json())
        #print(r)

    def do_login(self, arg):
        if(len(arg) != 3):
            print('[!] login <email> <password>')
            return

        # = {'email':'test@gmail.com', 'password':'test123'}
        r = requests.post('http://localhost:8000/api/login')
        pprint.pprint(r.json())

    def do_pw(self, arg):
        arg = arg.split(' ')
        if(len(arg) != 1):
            print('[!] pw <email>')
            return
        
        email = arg[0]
        payload = {
            'email': arg[0]
        }
        data=json.dumps(payload)

        r = requests.post('http://localhost:8000/api/password_reset', data)
        pprint.pprint(r.json())

    def do_ov(self, arg):
        #print(len(arg))
        if(len(arg) != 0):
            print('[!] overview')
            return
        data = {'email':'test@gmail.com', 'password':'test123'}
        r = requests.get('http://localhost:8000/api/')
        pprint.pprint(r.json())

    def do_h(self, arg):
        helpMsg = '\n'
        helpMsg += '='*80 + '\n'
        helpMsg += '\tREST API Commands\t\tHTTP Method\t\tDescription\n'
        helpMsg += '-'*80 + '\n'

        helpMsg += '\tlogin <email> <password>\tPOST\t\taccount login\n'
        helpMsg += '\tpw <email>\t\t\tPOST\t\treset password\n'
        helpMsg += '\tov \t\t\t\tGET\t\tapi overview\n'
        helpMsg += '\tca <email> <password>\t\tGET\t\tcreate account\n\t\t   <first-name>\n\t\t   <last-name>\n\t\t\n'
        helpMsg += '\n'

        helpMsg += '='*80 + '\n'
        helpMsg += '\tCLI Command\t\t\tDescription\n'
        helpMsg += '-'*80 + '\n'

        helpMsg += '\tcls \t\t\t\tclear screen\n'
        helpMsg += '\tq \t\t\t\tquit\n'
        helpMsg += '\th \t\t\t\thelp\n'
        helpMsg += '-'*80 + '\n'
        print(helpMsg)

    def do_cls(self, arg):
        os.system('cls')

    def do_q(self, arg):
        exit()

def main():
    Shell().cmdloop()

if __name__ == '__main__':
    main()