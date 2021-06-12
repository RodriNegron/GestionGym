
import java.io.*;

public class ManejoDeArchivos extends ObjectOutputStream
{
    private boolean append;
    private boolean initialized;
    private DataOutputStream dout;

    protected ManejoDeArchivos(boolean append) throws IOException, SecurityException
    {
        super();
        this.append = append;
        this.initialized = true;
    }

    public ManejoDeArchivos(OutputStream out, boolean append) throws IOException
    {
        super(out);
        this.append = append;
        this.initialized = true;
        this.dout = new DataOutputStream(out);
        this.writeStreamHeader();
    }

    @Override
    protected void writeStreamHeader() throws IOException
    {
        if (!this.initialized || this.append) return;
        if (dout != null) {
            dout.writeShort(STREAM_MAGIC);
            dout.writeShort(STREAM_VERSION);
        }
    }

    public static void mostrarArchivo(File file)
    {
        Object aux;
        String nombreDelArchivo = file.getName();

        try	{

            FileInputStream bn = new FileInputStream(nombreDelArchivo);
            ObjectInputStream fobj = new ObjectInputStream(bn);
            aux = fobj.readObject();

            while(aux!=null)
            {
                System.out.println(aux.toString());
                aux = fobj.readObject();
            }
            fobj.close();
        }catch(ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }catch (IOException e) {
        }
    }

    public static void agregarObjectSAlArchivo(File file, Object obj )
    {
        String nombreDelArchivo = file.getName();
        boolean append = file.exists();
        try
        {
             try
            {
                FileOutputStream fout = new FileOutputStream(file, append);
                @SuppressWarnings("resource")
                ManejoDeArchivos oout = new ManejoDeArchivos(fout, append);
                oout.writeObject(obj);
            }
            catch (FileNotFoundException e1)
            {
                e1.printStackTrace();
            } catch (IOException e1)
            {
                e1.printStackTrace();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}