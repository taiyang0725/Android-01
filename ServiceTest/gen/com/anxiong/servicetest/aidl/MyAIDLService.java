/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: F:\\Android\\ServiceTest\\src\\com\\anxiong\\servicetest\\aidl\\MyAIDLService.aidl
 */
package com.anxiong.servicetest.aidl;
public interface MyAIDLService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.anxiong.servicetest.aidl.MyAIDLService
{
private static final java.lang.String DESCRIPTOR = "com.anxiong.servicetest.aidl.MyAIDLService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.anxiong.servicetest.aidl.MyAIDLService interface,
 * generating a proxy if needed.
 */
public static com.anxiong.servicetest.aidl.MyAIDLService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.anxiong.servicetest.aidl.MyAIDLService))) {
return ((com.anxiong.servicetest.aidl.MyAIDLService)iin);
}
return new com.anxiong.servicetest.aidl.MyAIDLService.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_plus:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _result = this.plus(_arg0);
reply.writeNoException();
reply.writeString(_result);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.anxiong.servicetest.aidl.MyAIDLService
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public java.lang.String plus(java.lang.String a) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(a);
mRemote.transact(Stub.TRANSACTION_plus, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_plus = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
public java.lang.String plus(java.lang.String a) throws android.os.RemoteException;
}
