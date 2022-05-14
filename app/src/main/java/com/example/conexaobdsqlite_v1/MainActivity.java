    package com.example.conexaobdsqlite_v1;

    import android.os.Bundle;

    import com.example.conexaobdsqlite_v1.AcessoBD;
    import com.example.conexaobdsqlite_v1.R;
    import com.example.conexaobdsqlite_v1.Usuario;
    import com.google.android.material.floatingactionbutton.FloatingActionButton;
    import com.google.android.material.snackbar.Snackbar;

    import androidx.appcompat.app.AppCompatActivity;
    import androidx.appcompat.widget.Toolbar;

    import android.view.View;
    import android.view.Menu;
    import android.view.MenuItem;
    import android.widget.AdapterView;
    import android.widget.ArrayAdapter;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.ListView;
    import android.widget.Toast;

    import java.util.List;

    public class MainActivity extends AppCompatActivity {

        Button bAddUsuario;
        Button bListaUsuarios;
        Button bAtualizarUsuario;
        EditText etNomeUsuario;
        EditText etIdadeUsuario;
        EditText etCodigoUsuario;
        ListView lvUsuarios;

        ArrayAdapter usuarioArrayAdapter;
        AcessoBD acessoBD;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);


            bAddUsuario = findViewById(R.id.bAddUsuario);
            bListaUsuarios = findViewById(R.id.bListaUsuarios);
            bAtualizarUsuario = findViewById(R.id.bAtualizar);
            etNomeUsuario =  findViewById(R.id.ptNomeUsuario);
            etIdadeUsuario = findViewById(R.id.ptIdadeUsuario);
            etCodigoUsuario = findViewById(R.id.ptCodUsuario);
            lvUsuarios = findViewById(R.id.lv_usuarios);


            acessoBD = new AcessoBD(MainActivity.this);
            mostrarUsuariosNaListView(acessoBD);

            bAddUsuario.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //aqui acontece a ação


                    //Declaração de um objeto usuário da (nossa) classe Usuário
                    Usuario usuario=null ;//null para inicializar

                    //Bloco try para "tentar" executar as ações esperadas. O catch é para "remediar" ou "avisar" algo que não foi realizado no bloco try como esperado.
                    try {
                        usuario = new Usuario(-1, etNomeUsuario.getText().toString(), Integer.parseInt(etIdadeUsuario.getText().toString()));

                        boolean sucesso = acessoBD.adicionarUsuario(usuario);

                        mostrarUsuariosNaListView(acessoBD);
                        Toast.makeText(MainActivity.this, "Sucesso:"+sucesso, Toast.LENGTH_SHORT).show();

                    }catch (NumberFormatException e){
                        Toast.makeText(MainActivity.this, "Erro na conversão de uma String para int: Idade não corresponde a número!", Toast.LENGTH_LONG).show();
                    }catch (Exception e){
                        Toast.makeText(MainActivity.this, "Erro na criação do usuário!", Toast.LENGTH_LONG).show();
                        usuario=new Usuario(-1,"erro", 0);
                    }






                }
            });

            bListaUsuarios.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //aqui acontece a ação

                   /* //AcessoBD acessoBD = new AcessoBD(MainActivity.this);
                    List<Usuario> todosUsuarios = acessoBD.getListaUsuarios();//Dentro de <> está o tipo de usuário a ser inserido na lista

                    usuarioArrayAdapter = new ArrayAdapter<Usuario>(MainActivity.this, android.R.layout.simple_list_item_1, acessoBD.getListaUsuarios());//Dentro de <> está o tipo de objeto que será adicionado na lista


                    //Para o próximo passo, adicionar no content_main.xml o objeto da pallete que encontra-se em Legacy/ListView. É só arrastar o ListView para a tela. Fazer os ajustes de ancoragem.
                    //Também adicionar a variável aqui em MainActivity e associar ela ao componente com findViewById.
                    lvUsuarios.setAdapter(usuarioArrayAdapter);*/

                    mostrarUsuariosNaListView(acessoBD);

                    Toast.makeText(MainActivity.this, "Lista de usuários preenchida com sucesso", Toast.LENGTH_SHORT).show();


                }
            });


            /**
             * Evento de click na lista de usuários na intenção de excluir o usuário clicado.
             */
            lvUsuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    System.out.println("Captou click na lista!");
                    Usuario usuarioClicado  = (Usuario) parent.getItemAtPosition(position);
                    boolean excluiu = acessoBD.excluirUsuario(usuarioClicado);

                    mostrarUsuariosNaListView(acessoBD);

                    Toast.makeText(MainActivity.this, "Usuário excluído("+excluiu+"):"+usuarioClicado.toString(), Toast.LENGTH_SHORT).show();


                }
            });

    bAtualizarUsuario.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //aqui acontece a ação


            //Declaração de um objeto usuário da (nossa) classe Usuário
            Usuario usuario=null ;//null para inicializar

            //Bloco try para "tentar" executar as ações esperadas. O catch é para "remediar" ou "avisar" algo que não foi realizado no bloco try como esperado.
            try {
                usuario = new Usuario(Integer.parseInt(etCodigoUsuario.getText().toString()), etNomeUsuario.getText().toString(), Integer.parseInt(etIdadeUsuario.getText().toString()));

                boolean sucesso = acessoBD.atualizarUsuario(usuario);

                mostrarUsuariosNaListView(acessoBD);
                Toast.makeText(MainActivity.this, "Sucesso:"+sucesso, Toast.LENGTH_SHORT).show();

            }catch (NumberFormatException e){
                Toast.makeText(MainActivity.this, "Erro na conversão de uma String para int: Idade não corresponde a número!", Toast.LENGTH_LONG).show();
            }catch (Exception e){
                Toast.makeText(MainActivity.this, "Erro na criação do usuário!", Toast.LENGTH_LONG).show();
                usuario=new Usuario(-1,"erro", 0);
            }

        }
    });


        }

        private void mostrarUsuariosNaListView(AcessoBD acessoBD) {
            usuarioArrayAdapter = new ArrayAdapter<Usuario>(MainActivity.this, android.R.layout.simple_list_item_1, acessoBD.getListaUsuarios());//Dentro de <> está o tipo de objeto que será adicionado na lista
            lvUsuarios.setAdapter(usuarioArrayAdapter);
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }

    }
